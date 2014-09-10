package com.github.kmbulebu.dsc.it100.commands.write;

import java.io.Serializable;

import com.github.kmbulebu.dsc.it100.commands.ICommand;
import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class LabelsRequestCommand extends WriteCommand implements ICommand, ICommandHelp, Serializable {

	
	public LabelsRequestCommand() {
		super(CODE, "");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 969971460700679900L;
	public static final String CODE = "002";

	public String getDescription() {
		return "IT-100 responds by sending all programmable labels to the Software application. The IT-100 responds with: Broadcast Labels (570)";
	}

}
