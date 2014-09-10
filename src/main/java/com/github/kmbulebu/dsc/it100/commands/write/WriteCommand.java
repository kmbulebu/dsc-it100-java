package com.github.kmbulebu.dsc.it100.commands.write;

import java.io.Serializable;

import com.github.kmbulebu.dsc.it100.commands.ICommand;

public abstract class WriteCommand implements ICommand, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5266384632460680768L;
	private final String commandCode;
	private final String data;
	
	protected WriteCommand(String commandCode, String data) {
		this.commandCode = commandCode;
		this.data = data;
	}
	

	public final String getData() {
		return data;
	}


	public final String getCommandCode() {
		return commandCode;
	}
	

}
