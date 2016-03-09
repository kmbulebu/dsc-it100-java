package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class UserOpeningCommand extends BaseUserCommand implements ICommandHelp {

	public static final String CODE = "750";

	@Override
	public String getDescription() {
		return "This command indicates that a partition has been disarmed by a user";
	}

	@Override
	public String toString() {
		return "UserOpeningCommand [toString()=" + super.toString() + "]";
	}
}
