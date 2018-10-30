package com.github.kmbulebu.dsc.it100;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;
import com.github.kmbulebu.dsc.it100.mina.codec.IT100CodecFactory;
import com.github.kmbulebu.dsc.it100.mina.filters.CommandLogFilter;
import com.github.kmbulebu.dsc.it100.mina.filters.PollKeepAliveFilter;
import com.github.kmbulebu.dsc.it100.mina.filters.StatusRequestFilter;
import com.github.kmbulebu.dsc.it100.rxjava.ReadCommandOnSubscribe;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.apache.mina.handler.demux.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import rx.Observable;
import rx.Observer;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

import java.net.SocketAddress;

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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IT100.class);
	
	private final Configuration configuration;
	
	private ConnectableObservable<ReadCommand> readObservable;
	
	private PublishSubject<WriteCommand> writeObservable;
	
	public IT100(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Begin communicating with the IT-100.
	 * 
	 * @throws Exception If an error occurs while connecting to the IT-100
	 */
	public void connect() throws Exception {		
		// Start up our MINA stuff 
		// Setup MINA codecs
		final IT100CodecFactory it100CodecFactory = new IT100CodecFactory();
		final ProtocolCodecFilter protocolCodecFilter = new ProtocolCodecFilter(it100CodecFactory);
		final CommandLogFilter loggingFilter = new CommandLogFilter(LOGGER, Level.DEBUG);
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
		readObservable = Observable.create(readCommandObservable).publish();
		readObservable.connect();
		
		// Create a write observer.
		writeObservable = PublishSubject.create();
		writeObservable.subscribe(new Observer<WriteCommand>() {

			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onNext(WriteCommand command) {
				session.write(command);
			}
		});
		
		
	}
	
	 public Observable<ReadCommand> getReadObservable() {
		 if (readObservable == null) {
			 throw new IllegalStateException("You must call connect() first.");
		 }
		 return readObservable;
	}
	 
	 public PublishSubject<WriteCommand> getWriteObservable() {
		 if (readObservable == null) {
			 throw new IllegalStateException("You must call connect() first.");
		 }
		return writeObservable;
	}

	/**
	 * Sends a command to the IT-100. 
	 * 
	 * Shortcut for getWriteObservable().onNext()
	 * @param command The command to send to the IT-100.
	 */
	public void send(WriteCommand command) {
		getWriteObservable().onNext(command);
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
	

	public static interface Configuration {
	
		public IoConnector getConnector();
		
		public SocketAddress getAddress();
		
		public long getConnectTimeout();
		
		public int getStatusPollingInterval();
	
	}

}
