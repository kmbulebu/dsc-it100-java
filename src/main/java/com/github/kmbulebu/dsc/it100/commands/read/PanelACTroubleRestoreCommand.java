package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PanelACTroubleRestoreCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5217538058073820922L;
	public static final String CODE = "803";

	public String getDescription() {
		return "Indicates that AC power to the panel has been restored.";
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
		return "PanelACTroubleRestoreCommand [getDescription()=" + getDescription() + "]";
	}
	
}
