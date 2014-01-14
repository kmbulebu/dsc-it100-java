package com.oakcity.dsc.it100.commands.write;

import java.io.Serializable;

import com.oakcity.dsc.it100.commands.ICommand;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PollCommand implements ICommand, ICommandHelp, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4145502177013901841L;
	
	public static final String CODE = "000";

	public String getDescription() {
		return "Verifies communication channel with IT-100. IT-100 responds with: Command Acknowledge (500)";
	}

	public String getCommandCode() {
		return CODE;
	}

	public String getData() {
		return "";
	}

}
