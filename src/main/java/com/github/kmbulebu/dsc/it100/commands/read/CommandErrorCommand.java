package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class CommandErrorCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2018558966856616591L;
	public static final String CODE = "501";

	public String getDescription() {
		return "This command indicates that a Command has been received with a bad checksum. No additional data is available.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		return;
	}

	@Override
	public String toString() {
		return "CommandErrorCommand [toString()=" + super.toString() + "]";
	}
	
	

}
