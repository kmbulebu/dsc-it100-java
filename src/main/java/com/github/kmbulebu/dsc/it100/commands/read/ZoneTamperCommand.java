
package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class ZoneTamperCommand extends BasePartitionZoneCommand implements ICommandHelp {

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
	
}
