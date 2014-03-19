package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class KeybusFaultRestoredCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4738426219154414469L;
	public static final String CODE = "897";

	public String getDescription() {
		return "This command indicates a keybus fault has has been restored.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 0) {
			throw new CommandDataParseException("Expected data length of 0 bytes, received " + dataString.length());
		}
	}

	@Override
	public String toString() {
		return "KeybusFaultRestoredCommand [toString()=" + super.toString() + "]";
	}
	
}
