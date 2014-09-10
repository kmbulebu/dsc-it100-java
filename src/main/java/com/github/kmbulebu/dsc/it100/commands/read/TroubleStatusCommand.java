package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class TroubleStatusCommand extends BasePartitionCommand implements
		ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708765578935248460L;
	
	public static final String CODE = "840";

	public String getDescription() {
		return "This command sends the general trouble status that the trouble LED on a keypad normally displays when there is a trouble present on system.";
	}

	@Override
	public String toString() {
		return "TroubleStatusCommand [toString()=" + super.toString() + "]";
	}
	
	

}
