package com.oakcity.dsc.it100.mina.filters;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

import com.oakcity.dsc.it100.commands.read.ReadCommand;
import com.oakcity.dsc.it100.commands.write.WriteCommand;

public class CommandLogFilter extends IoFilterAdapter {
	
	private final Logger logger;
	private final Level level;
	
	public CommandLogFilter(Logger logger, Level level) {
		this.logger = logger;
		this.level = level;
	}
	
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		if (message instanceof ReadCommand) {
			logger.log(level, new ReceivedMarker(), message.toString());
		}
		super.messageReceived(nextFilter, session, message);
	}
	
	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		if (writeRequest.getMessage() instanceof WriteCommand) {
			logger.log(level, new SentMarker(), writeRequest.getMessage().toString());
		}
		super.messageSent(nextFilter, session, writeRequest);
	}
	
	private static class ReceivedMarker implements Marker {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5190100565468306990L;

		@Override
		public String getName() {
			return "RECEIVED";
		}

		@Override
		public Marker getParent() {
			return null;
		}

		@Override
		public boolean isInstanceOf(Marker m) {
			return (m instanceof ReceivedMarker);
		}

		@Override
		public boolean isInstanceOf(String name) {
			return "RECEIVED".equals(name);
		}
		
		@Override
		public String toString() {
			return "RECEIVED";
		}
		
	}
	
	private static class SentMarker implements Marker {


		/**
		 * 
		 */
		private static final long serialVersionUID = 5213322286168888154L;

		@Override
		public String getName() {
			return "SENT";
		}

		@Override
		public Marker getParent() {
			return null;
		}

		@Override
		public boolean isInstanceOf(Marker m) {
			return (m instanceof SentMarker);
		}

		@Override
		public boolean isInstanceOf(String name) {
			return "SENT".equals(name);
		}
		
		@Override
		public String toString() {
			return "SENT";
		}
		
	}

}
