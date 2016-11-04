package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class SpecialClosingCommand extends BasePartitionCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CODE = "701";

	@Override
	public String getDescription() {
		return "Indicates that a partition has been armed by one of the following methods: Quick Arm, Auto Arm, Keyswitch, DLS software, Wireless Key.";
	}

	@Override
	public String toString() {
		return "SpecialClosingCommand [toString()=" + super.toString() + "]";
	}
}
