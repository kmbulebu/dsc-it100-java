package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PartitionNotReadyCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	public static final String CODE = "651";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968618089582632808L;
	public String getDescription() {
		return "This command indicates that the partition cannot be armed (zones open, trouble present, etc).";
	}
	
	@Override
	public String toString() {
		return "PartitionNotReadyCommand [toString()=" + super.toString() + "]";
	}

}
