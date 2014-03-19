package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class EntryDelayInProgressCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650237444659809552L;
	public static final String CODE = "657";

	public String getDescription() {
		return "This command indicates that a partition is in Entry Delay.";
	}

	@Override
	public String toString() {
		return "EntryDelayInProgressCommand [toString()=" + super.toString() + "]";
	}

}
