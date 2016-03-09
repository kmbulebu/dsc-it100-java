package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class UserClosingCommand extends BaseUserCommand implements ICommandHelp {

	public static final String CODE = "700";

	@Override
	public String getDescription() {
		return "This command indicates that a partition has been armed by a user – sent at the end of exit delay.";
	}

	@Override
	public String toString() {
		return "UserClosingCommand [toString()=" + super.toString() + "]";
	}
}
