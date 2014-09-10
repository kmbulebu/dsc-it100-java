package com.github.kmbulebu.dsc.it100.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

import com.github.kmbulebu.dsc.it100.commands.ICommand;
import com.github.kmbulebu.dsc.it100.commands.util.CommandChecksum;

public class IT100Encoder extends TextLineEncoder {
	
	public IT100Encoder() {
		super(Charset.forName("US-ASCII"), LineDelimiter.CRLF);
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		if (message instanceof ICommand) {
			ICommand command = (ICommand) message;
			StringBuilder builder = new StringBuilder();
			builder.append(command.getCommandCode());
			builder.append(command.getData());
			builder.append(CommandChecksum.calculateChecksum(command.getCommandCode(), command.getData()));
			super.encode(session, builder.toString(), out);
		}
		
	}
	
	

}
