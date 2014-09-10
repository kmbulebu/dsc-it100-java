package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class SystemBellTroubleRestoreCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435059749531186687L;
	public static final String CODE = "807";

	public String getDescription() {
		return "This command indicates that the bell trouble has been restored.";
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
		return "SystemBellTroubleRestoreCommand [getDescription()=" + getDescription() + "]";
	}
	
	
	
}
