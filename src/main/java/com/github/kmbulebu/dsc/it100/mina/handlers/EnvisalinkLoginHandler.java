package com.github.kmbulebu.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.github.kmbulebu.dsc.it100.commands.read.EnvisalinkLoginInteractionCommand;
import com.github.kmbulebu.dsc.it100.commands.write.EnvisalinkNetworkLoginCommand;

public class EnvisalinkLoginHandler implements MessageHandler<EnvisalinkLoginInteractionCommand> {
	
	private final String password;
	
	public EnvisalinkLoginHandler(String password) {
		this.password = password;
	}

	@Override
	public void handleMessage(IoSession session, EnvisalinkLoginInteractionCommand message) throws Exception {
		if (EnvisalinkLoginInteractionCommand.LoginCode.PASSWORD_REQUESTED == message.getLoginCode()) {
			session.write(new EnvisalinkNetworkLoginCommand(password));
		}
	}
	
}
