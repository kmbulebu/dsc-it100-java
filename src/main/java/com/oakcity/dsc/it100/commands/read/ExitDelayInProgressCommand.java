package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class ExitDelayInProgressCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3244101308667441113L;
	public static final String CODE = "656";

	public String getDescription() {
		return "This command indicates that a partition is in Exit Delay.";
	}

	@Override
	public String toString() {
		return "ExitDelayInProgressCommand [toString()=" + super.toString() + "]";
	}

}
