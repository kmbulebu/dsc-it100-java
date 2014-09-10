package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class CommandOutputInProgressCommand extends BasePartitionCommand implements
		ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120619474389903464L;
	public static final String CODE = "660";

	public String getDescription() {
		return "This command indicates that a partition is in command output mode of operation.";
	}

	@Override
	public String toString() {
		return "CommandOutputInProgressCommand [toString()=" + super.toString() + "]";
	}

}
