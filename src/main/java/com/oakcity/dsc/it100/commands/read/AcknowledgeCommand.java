package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class AcknowledgeCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8566976140528732230L;

	public static final String CODE = "500";
	
	private String commandAcknowledged = null;

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 3) {
			throw new CommandDataParseException("Expected data length of 3 bytes, received " + dataString.length());
		}
		commandAcknowledged = new String(dataString);
		return;
	}
	
	public String getCommandAcknowledged() {
		return commandAcknowledged;
	}

	@Override
	public String toString() {
		return "AcknowledgeCommand [commandAcknowledged=" + commandAcknowledged
				+ ", toString()=" + super.toString() + "]";
	}

	public String getDescription() {
		return "This command indicates that a Command has been received by IT-100. This command is always the first response to a command from the applica- tion unless there is a checksum error then a Command Error (501) is sent.";
	}
	
}
