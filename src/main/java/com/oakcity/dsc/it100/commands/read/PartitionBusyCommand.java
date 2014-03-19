package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionBusyCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4647990642115096757L;
	public static final String CODE = "673";

	public String getDescription() {
		return "This command indicates that the partition is busy. (No documention in IT-100 developer guide.)";
	}

	@Override
	public String toString() {
		return "PartitionBusyCommand [toString()=" + super.toString() + "]";
	}

}
