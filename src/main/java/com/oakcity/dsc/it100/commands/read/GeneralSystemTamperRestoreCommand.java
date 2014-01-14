package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPanel;
import com.oakcity.dsc.it100.IPanelStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class GeneralSystemTamperRestoreCommand extends ReadCommand implements ICommandHelp, IPanelStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -451799995595862398L;
	public static final String CODE = "830";

	public String getDescription() {
		return "This command indicates that a tamper has been restored on an alarm system module.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 0) {
			throw new CommandDataParseException("Expected data length of 0 bytes, received " + dataString.length());
		}
	}

	@Override
	public boolean isSystemChanged() {
		return true;
	}

	@Override
	public void updateSystem(IPanel system) {
		system.setInGeneralSystemTamper(false);
	}
	

	
}
