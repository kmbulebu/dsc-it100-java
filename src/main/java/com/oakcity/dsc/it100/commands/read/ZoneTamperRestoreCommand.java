
package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ZoneTamperRestoreCommand extends BasePartitionZoneCommand implements ICommandHelp, IZoneStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7725456176619204481L;
	public static final String CODE = "604";

	public String getDescription() {
		return "This command indicates that a zone tamper condition (and associated partition) has been restored.";
	}

	@Override
	public String toString() {
		return "ZoneTamperRestoreCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isZoneChanged() {
		return true;
	}

	@Override
	public void updateZone(IZone zone) {
		zone.setInTamper(false);
	}
	
	
	
}
