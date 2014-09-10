package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PanelBatteryTroubleCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2927807581385349072L;
	public static final String CODE = "800";

	public String getDescription() {
		return "This command indicates that the panel has a low battery.";
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
		return "PanelBatteryTroubleCommand [getDescription()=" + getDescription() + "]";
	}
	
	
	
}
