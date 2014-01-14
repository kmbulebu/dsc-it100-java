
package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ZoneTamperCommand extends BasePartitionZoneCommand implements ICommandHelp, IZoneStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1928980622336431100L;
	public static final String CODE = "603";

	public String getDescription() {
		return "This IT-100 command indicates that a zone and associated partition has a tamper condition.";
	}

	@Override
	public String toString() {
		return "ZoneTamperCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isZoneChanged() {
		return true;
	}

	@Override
	public void updateZone(IZone zone) {
		zone.setInTamper(true);
	}
	
	
	
	
	
}
