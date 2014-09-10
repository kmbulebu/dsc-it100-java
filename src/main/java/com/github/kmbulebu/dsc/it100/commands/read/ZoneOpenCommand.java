package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class ZoneOpenCommand extends BaseZoneCommand implements ICommandHelp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4620136684068544660L;

	public static final String CODE = "609";
	
	public String getDescription() {
		return "This command indicates the general status of the zone as open.";
	}

	@Override
	public String toString() {
		return "ZoneOpenCommand [toString()=" + super.toString() + "]";
	}
	
}
