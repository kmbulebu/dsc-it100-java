package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPanel;
import com.oakcity.dsc.it100.IPanelStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class KeybusFaultCommand extends ReadCommand implements ICommandHelp, IPanelStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5348913057613069351L;
	public static final String CODE = "896";

	public String getDescription() {
		return "This command indicates a keybus fault has occured.";
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
		return "KeybusFaultCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isSystemChanged() {
		return true;
	}

	@Override
	public void updateSystem(IPanel system) {
		system.setInKeybusFault(true);
	}
	

	
}
