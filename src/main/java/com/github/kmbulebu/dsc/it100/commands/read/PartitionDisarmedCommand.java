package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PartitionDisarmedCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3816440777910452558L;
	public static final String CODE = "655";

	public String getDescription() {
		return "This command indicates that a partition has been disarmed.";
	}

	@Override
	public String toString() {
		return "PartitionDisarmedCommand [toString()=" + super.toString() + "]";
	}

}
