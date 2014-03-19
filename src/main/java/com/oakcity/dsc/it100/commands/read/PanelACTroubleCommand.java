package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PanelACTroubleCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -94076149793102204L;
	public static final String CODE = "802";

	public String getDescription() {
		return "This command indicates that AC power to the panel has been removed.";
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
		return "PanelACTroubleCommand [getDescription()=" + getDescription() + "]";
	}
	
	
	
}
