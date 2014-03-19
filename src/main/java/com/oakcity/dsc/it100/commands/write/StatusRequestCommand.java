package com.oakcity.dsc.it100.commands.write;

import java.io.Serializable;

import com.oakcity.dsc.it100.commands.ICommand;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class StatusRequestCommand extends WriteCommand implements ICommand, ICommandHelp, Serializable {

	public static final String CODE = "001";
	
	public StatusRequestCommand() {
		super(StatusRequestCommand.CODE, "");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 969971460700679900L;


	public String getDescription() {
		return "IT-100 responds with general zone, partition, and trouble status updates to the Control Software Application. Troubles are limited to the status of the trouble LED on a keypad. Only the partitions that have been detected, and their trouble states, will be displayed. When connected to a Pow- erSeries panel, the module will send the status of: all zones, troubles, and status of enabled partitions only.";
	}

}
