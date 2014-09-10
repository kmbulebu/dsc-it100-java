package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class InvalidAccessCodeCommand extends BasePartitionCommand implements
		ICommandHelp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3079851218652998702L;
	public static final String CODE = "670";

	public String getDescription() {
		return "This command indicates that an access code that was entered was invalid. If an access code was sent by the IT100 and this indication comes from the alarm panel within one second, then this command will be sent to the application. Otherwise this indication will be ignored and this command will not be sent.";
	}

	@Override
	public String toString() {
		return "InvalidAccessCodeCommand [toString()=" + super.toString() + "]";
	}

}
