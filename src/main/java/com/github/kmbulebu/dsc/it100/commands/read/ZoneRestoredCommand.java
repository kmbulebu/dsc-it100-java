package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class ZoneRestoredCommand extends BaseZoneCommand implements ICommandHelp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4762903399307437447L;

	public static final String CODE = "610";

	public String getDescription() {
		return "This command indicates the general status of the zone as secure.";
	}

	@Override
	public String toString() {
		return "ZoneRestoredCommand [toString()=" + super.toString() + "]";
	}
	
}
