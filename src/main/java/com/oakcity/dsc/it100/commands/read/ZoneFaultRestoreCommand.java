package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ZoneFaultRestoreCommand extends BaseZoneCommand implements ICommandHelp, IZoneStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3404226981717711602L;
	public static final String CODE = "606";
	
	public String getDescription() {
		return "This command indicates that a zone fault condition has been restored.";
	}

	@Override
	public String toString() {
		return "ZoneFaultRestoreCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isZoneChanged() {
		return true;
	}

	@Override
	public void updateZone(IZone zone) {
		zone.setInFault(false);
	}
	
}
