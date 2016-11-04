package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class DoorChimeStatusCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CODE = "907";

	@Override
	public String getDescription() {
		return "This command is sent when the application requests door chime for the current partition.";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 0) {
			throw new CommandDataParseException("Expected data length of 0 bytes, received " + dataString.length());
		}
	}
}
