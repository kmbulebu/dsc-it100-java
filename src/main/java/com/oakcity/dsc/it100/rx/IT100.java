package com.oakcity.dsc.it100.rx;

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

/**
 * API Main Entry point.
 * 
 * Enables receiving and sending commands to/from the IT-100 integration module.
 * 
 * Connect directly to the IT-100 via RS-232 serial port, or over the network using
 * ser2net or an equivalent tool. 
 * 
 * 
 * @author Kevin Bulebush
 *
 */
public class IT100 {
	
	private IoConnector connector = null;
	private IoSession session = null;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final Configuration configuration;
	
	public IT100(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Begin communicating with the IT-100.
	 * 
	 * @return An Observable for reading commands sent by the IT-100 to the application.
	 * @throws Exception If an error occurs while connecting to the IT-100
	 */
	public Observable<ReadCommand> connect() throws Exception {		
		// Start up our MINA stuff 
		// Setup MINA codecs
		final IT100CodecFactory it100CodecFactory = new IT100CodecFactory();
		final ProtocolCodecFilter protocolCodecFilter = new ProtocolCodecFilter(it100CodecFactory);
		final CommandLogFilter loggingFilter = new CommandLogFilter(LOGGER, Level.INFO);
		final PollKeepAliveFilter pollKeepAliveFilter = new PollKeepAliveFilter(KeepAliveRequestTimeoutHandler.EXCEPTION);
		
		// Connect to system serial port.
		connector = configuration.getConnector();
		
		// Typical configuration
		connector.setConnectTimeoutMillis(configuration.getConnectTimeout());
		connector.getSessionConfig().setUseReadOperation(true);

		// Add IT100 codec to MINA filter chain to interpret messages.
		connector.getFilterChain().addLast("codec", protocolCodecFilter);
		connector.getFilterChain().addLast("logger", loggingFilter);   
	    connector.getFilterChain().addLast("keepalive", pollKeepAliveFilter);
	    
	    if (configuration.getStatusPollingInterval() != -1) {
	    	connector.getFilterChain().addLast("statusrequest", new StatusRequestFilter(configuration.getStatusPollingInterval()));
	    }
	    
	    final DemuxingIoHandler demuxIoHandler = new DemuxingIoHandler();
	    
	    // We don't need to subscribe to the messages we sent.
		demuxIoHandler.addSentMessageHandler(Object.class, MessageHandler.NOOP);
		
		// OnSubscribe will allow us to create an Observable to received messages.
		final ReadCommandOnSubscribe readCommandObservable = new ReadCommandOnSubscribe(demuxIoHandler);
		connector.setHandler(demuxIoHandler);
		 
		// Connect now
		final ConnectFuture future = connector.connect(configuration.getAddress()); 
		future.awaitUninterruptibly();

		// Get a reference to the session
		session = future.getSession(); 
		
		// Create and return our Observable for received IT-100 commands.
		return Observable.create(readCommandObservable);
	}

	/**
	 * Sends a command to the IT-100. 
	 * 
	 * Asynchronous. 
	 * @param command The command to send to the IT-100.
	 */
	public void send(WriteCommand command) {
		// TODO Return a Future that's independent of the MINA API.
		session.write(command).awaitUninterruptibly();
	}
	
	/**
	 * Stop communicating with the IT-100 and release the port.
	 * @throws Exception If there is an error while trying to close the port.
	 */
	public void disconnect() throws Exception {
		if (session != null) {
			session.getCloseFuture().awaitUninterruptibly();
		}
		if (connector != null) {
			connector.dispose();
		}
	}
	

}
