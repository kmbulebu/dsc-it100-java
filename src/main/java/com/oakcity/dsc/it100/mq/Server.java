package com.oakcity.dsc.it100.mq;

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

import rx.Observable;

import com.oakcity.dsc.it100.commands.read.ReadCommand;
import com.oakcity.dsc.it100.commands.write.WriteCommand;
import com.oakcity.dsc.it100.mina.codec.IT100CodecFactory;
import com.oakcity.dsc.it100.mina.filters.CommandLogFilter;
import com.oakcity.dsc.it100.mina.filters.PollKeepAliveFilter;
import com.oakcity.dsc.it100.mina.filters.StatusRequestFilter;
import com.oakcity.dsc.it100.mina.handlers.rx.ReadCommandOnSubscribe;

public class Server {
	
	private IoConnector connector = null;
	private IoSession session = null;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final Configuration configuration;
	
	public Server(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public Observable<ReadCommand> start() throws Exception {		
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
		final ReadCommandOnSubscribe readCommandObservable = new ReadCommandOnSubscribe(demuxIoHandler);
		connector.setHandler(demuxIoHandler);
		 
		// Connect now
		final ConnectFuture future = connector.connect(configuration.getAddress()); 
		future.awaitUninterruptibly();

		// Get a reference to the session
		session = future.getSession(); 
		
		return Observable.create(readCommandObservable);
	}

	public void send(WriteCommand command) {
		session.write(command).awaitUninterruptibly();
	}
	
	public void stop() throws Exception {
		if (session != null) {
			session.getCloseFuture().awaitUninterruptibly();
		}
		if (connector != null) {
			connector.dispose();
		}
	}
	

}
