package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPanel;
import com.oakcity.dsc.it100.IPanelStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PanelBatteryTroubleRestoreCommand extends ReadCommand implements ICommandHelp, IPanelStateChangeEvent {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -988521416091627986L;
	public static final String CODE = "801";

	public String getDescription() {
		return "This command indicates that the panel’s low battery has been restored.";
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
		system.setPanelBatteryTrouble(false);
	}

	@Override
	public String toString() {
		return "PanelBatteryTroubleRestoreCommand [getDescription()=" + getDescription() + "]";
	}
	
	
	
}
