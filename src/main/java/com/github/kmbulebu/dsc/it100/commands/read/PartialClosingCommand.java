package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PartialClosingCommand extends BasePartitionCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CODE = "702";

	@Override
	public String getDescription() {
		return "This command indicates that a partition has been armed but one or more zones have been bypassed.";
	}

	@Override
	public String toString() {
		return "PartialClosingCommand [toString()=" + super.toString() + "]";
	}
}
