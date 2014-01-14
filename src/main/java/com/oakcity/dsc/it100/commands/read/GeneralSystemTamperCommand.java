package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPanel;
import com.oakcity.dsc.it100.IPanelStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class GeneralSystemTamperCommand extends ReadCommand implements ICommandHelp, IPanelStateChangeEvent {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 877989784917405338L;
	public static final String CODE = "829";

	public String getDescription() {
		return "This command indicates that a tamper has ocurred on an alarm system module.";
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
		system.setInGeneralSystemTamper(true);
	}

	@Override
	public String toString() {
		return "GeneralSystemTamperCommand [getDescription()=" + getDescription() + "]";
	}
	
	

	
}
