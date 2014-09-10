package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.DataStringBuilder;

public class BaudRateChangeCommand extends WriteCommand implements ICommandHelp {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 610371346540454935L;

	public static final String CODE = "580";
	
	private final BaudRate baudRate;

	public BaudRateChangeCommand(BaudRate baudRate) {
		super(CODE, new DataStringBuilder().appendIntData(baudRate.ordinal(), 1).build());
		this.baudRate = baudRate;
	}


	public String getDescription() {
		return "This command changes the Baud Rate.";
	}


	public BaudRate getBaudRate() {
		return baudRate;
	}
	
	public static enum BaudRate {
		BAUD_9600,
		BAUD_19200,
		BAUD_38400,
		BAUD_57600,
		BAUD_115200;

	}
	
	


}
