
package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ZoneAlarmCommand extends BasePartitionZoneCommand implements ICommandHelp, IZoneStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 459646483651265596L;

	public static final String CODE = "601";

	public String getDescription() {
		return "This IT-100 command indicates that a zone and associated partition has gone into alarm.";
	}

	@Override
	public String toString() {
		return "ZoneAlarmCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isZoneChanged() {
		return true;
	}

	@Override
	public void updateZone(IZone zone) {
		zone.setIsInAlarm(true, getPartition());
	}
	
	
	
}
