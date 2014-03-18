package com.oakcity.dsc.it100.mina.filters;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.oakcity.dsc.it100.commands.read.AcknowledgeCommand;
import com.oakcity.dsc.it100.commands.write.StatusRequestCommand;

public class StatusRequestFilter extends KeepAliveFilter {
	
	public static final int DEFAULT_REQUEST_TIMEOUT = 5; // 5 seconds

	public StatusRequestFilter(int requestInterval) {
		super(new StateRequestMessageFactory(), KeepAliveRequestTimeoutHandler.NOOP);
		super.setRequestInterval(requestInterval);
		super.setRequestTimeout(DEFAULT_REQUEST_TIMEOUT);
	}
	
	public static class StateRequestMessageFactory implements KeepAliveMessageFactory {

		@Override
		public boolean isRequest(IoSession session, Object message) {
			return false;
		}

		@Override
		public boolean isResponse(IoSession session, Object message) {
			if (message instanceof AcknowledgeCommand) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Object getRequest(IoSession session) {
			return new StatusRequestCommand();
		}

		@Override
		public Object getResponse(IoSession session, Object request) {
			return null;
		}
		
	}

}
