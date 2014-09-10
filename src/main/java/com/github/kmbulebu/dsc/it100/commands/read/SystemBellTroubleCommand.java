package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class SystemBellTroubleCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -534890826030665549L;
	public static final String CODE = "806";

	public String getDescription() {
		return "This command indicates that an open circuit has been detected across the bell terminals.";
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
		return "SystemBellTroubleCommand [getDescription()=" + getDescription() + "]";
	}
	
	
	
}
