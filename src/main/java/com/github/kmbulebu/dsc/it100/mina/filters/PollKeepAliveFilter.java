package com.github.kmbulebu.dsc.it100.mina.filters;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.github.kmbulebu.dsc.it100.commands.read.AcknowledgeCommand;
import com.github.kmbulebu.dsc.it100.commands.write.PollCommand;

public class PollKeepAliveFilter extends KeepAliveFilter {

	public PollKeepAliveFilter(KeepAliveRequestTimeoutHandler policy) {
		super(new PollKeepAliveMessageFactory(), policy);
	}

	
	/**
	 * Sends 200 Poll command, expects 500 Acknowledge back.
	 * @author kmbulebu
	 *
	 */
	public static class PollKeepAliveMessageFactory implements KeepAliveMessageFactory {

		@Override
		public Object getRequest(IoSession session) {
			return new PollCommand();
		}

		@Override
		public Object getResponse(IoSession session, Object request) {
			return null;
		}

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

	}
}
