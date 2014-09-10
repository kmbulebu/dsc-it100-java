package com.github.kmbulebu.dsc.it100.commands.write;

import java.io.Serializable;

import com.github.kmbulebu.dsc.it100.commands.ICommand;
import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PollCommand extends WriteCommand implements ICommand, ICommandHelp, Serializable {
	
	public PollCommand() {
		super(CODE, "");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145502177013901841L;
	
	public static final String CODE = "000";

	public String getDescription() {
		return "Verifies communication channel with IT-100. IT-100 responds with: Command Acknowledge (500)";
	}

}
