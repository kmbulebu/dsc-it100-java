package com.oakcity.dsc.it100.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;

import com.oakcity.dsc.it100.commands.read.ReadCommand;
import com.oakcity.dsc.it100.commands.read.ReadCommandFactory;

public class IT100Decoder extends TextLineDecoder {

	private ReadCommandFactory commandFactory = new ReadCommandFactory();

	public IT100Decoder() {
		super(Charset.forName("US-ASCII"), LineDelimiter.CRLF);
	}

	@Override
	protected void writeText(IoSession session, String text, ProtocolDecoderOutput out) {
		ReadCommand command = commandFactory.parseCommand(text);
		out.write(command);
	}

}
