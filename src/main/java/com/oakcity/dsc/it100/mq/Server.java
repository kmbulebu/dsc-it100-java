package com.oakcity.dsc.it100.mq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.IStateChangeEvent;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.mina.codec.IT100CodecFactory;
import com.oakcity.dsc.it100.mina.filters.CommandLogFilter;
import com.oakcity.dsc.it100.mina.filters.PollKeepAliveFilter;
import com.oakcity.dsc.it100.mina.filters.StatusRequestFilter;
import com.oakcity.dsc.it100.mina.handlers.FanOutStateHandler;
import com.oakcity.dsc.it100.mq.internal.MqZoneStateHandler;
import com.oakcity.dsc.it100.mq.internal.ZoneMessageListener;

public class Server {
	
	private BrokerService broker = null;
	
	private IoConnector connector = null;
	private IoSession session = null;
	
	private List<ZoneOpenListener> zoneOpenListeners = new ArrayList<ZoneOpenListener>();
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final Configuration configuration;
	
	public Server(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@SuppressWarnings("unchecked")
	public void start() throws Exception {
		// Start MQ broker
		startBroker();	
		
		// Start up our MINA stuff 
		// Setup MINA codecs
		final IT100CodecFactory it100CodecFactory = new IT100CodecFactory();
		final ProtocolCodecFilter protocolCodecFilter = new ProtocolCodecFilter(it100CodecFactory);
		final CommandLogFilter loggingFilter = new CommandLogFilter(LOGGER, Level.INFO);
		final PollKeepAliveFilter pollKeepAliveFilter = new PollKeepAliveFilter(KeepAliveRequestTimeoutHandler.EXCEPTION);
		final StatusRequestFilter statusRequestFilter = new StatusRequestFilter();	
		
		// Connect to system serial port.
		connector = configuration.getConnector();
		
		// Typical configuration
		connector.setConnectTimeoutMillis(configuration.getConnectTimeout());
		connector.getSessionConfig().setUseReadOperation(true);

		// Add IT100 codec to MINA filter chain to interpret messages.
		connector.getFilterChain().addLast("codec", protocolCodecFilter);
		connector.getFilterChain().addLast("logger", loggingFilter);   
	    connector.getFilterChain().addLast("keepalive", pollKeepAliveFilter);
	    connector.getFilterChain().addLast("statusrequest", statusRequestFilter);
	    
	    final DemuxingIoHandler demuxIoHandler = new DemuxingIoHandler();
		demuxIoHandler.addSentMessageHandler(Object.class, MessageHandler.NOOP);
		
		final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://embedded");
	    final Connection connection = connectionFactory.createConnection();
	    connection.start();
		final Session publishSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final MqZoneStateHandler zoneStateHandler = new MqZoneStateHandler(publishSession);	
		
		final FanOutStateHandler receiveHandler = new FanOutStateHandler();
		//receiveHandler.addMessageHandler(IPartitionStateChangeEvent.class, partitionStateHandler);
		receiveHandler.addMessageHandler(IZoneStateChangeEvent.class, zoneStateHandler);
		//receiveHandler.addMessageHandler(IPanelStateChangeEvent.class, panelStateHandler);
		//receiveHandler.addMessageHandler(IVirtualKeypadStateChangeEvent.class, virtualKeypadStateHandler);
		//receiveHandler.addMessageHandler(ICommandOutputStateChangeEvent.class, commandOutputsStateHandler);
		demuxIoHandler.addReceivedMessageHandler(IStateChangeEvent.class, receiveHandler);
		connector.setHandler(demuxIoHandler);
		 
		// Connect now
		final ConnectFuture future = connector.connect(configuration.getAddress()); 
		future.awaitUninterruptibly();

		// Get a reference to the session
		session = future.getSession(); 
		
		// Subscribe to our zone events so that API listeners receive them.
		final Session consumeSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer zoneConsumer = consumeSession.createConsumer(consumeSession.createTopic("zones"));
		zoneConsumer.setMessageListener(new ZoneMessageListener(getZoneOpenListeners()));
	}
	
	protected void startBroker() throws Exception {
		broker = new BrokerService();
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));
		broker.setBrokerName("embedded");
		broker.addConnector(connector);
		broker.start();
	}
	
	public void stop() throws Exception {
		if (broker != null) {
			broker.stop();
		}
		
		if (session != null) {
			session.getCloseFuture().awaitUninterruptibly();
		}
		if (connector != null) {
			connector.dispose();
		}
	}
	
	public List<ZoneOpenListener> getZoneOpenListeners() {
		return zoneOpenListeners;
	}

}
