package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionReadyToForceArmCommand extends BasePartitionCommand implements
		ICommandHelp {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4300966974243689867L;
	public static final String CODE = "653";

	public String getDescription() {
		return "This command indicates that a partition is in ready to Force Arm.";
	}

	@Override
	public String toString() {
		return "PartitionReadyToForceArmCommand [toString()=" + super.toString() + "]";
	}
}
