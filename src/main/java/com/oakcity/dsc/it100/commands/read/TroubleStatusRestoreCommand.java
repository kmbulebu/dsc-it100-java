package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class TroubleStatusRestoreCommand extends BasePartitionCommand implements
		ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6522904297712359385L;
	
	public static final String CODE = "841";

	public String getDescription() {
		return "This command sends the general trouble status that the trouble LED on a keypad normally displays when there are no troubles on system.";
	}

	@Override
	public String toString() {
		return "TroubleStatusRestoreCommand [toString()=" + super.toString() + "]";
	}
	
	

}
