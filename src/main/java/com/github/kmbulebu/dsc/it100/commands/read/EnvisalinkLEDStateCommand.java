package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.Arrays;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class EnvisalinkLEDStateCommand extends BaseEnvisalinkLEDCommand implements ICommandHelp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1986910085261715304L;

	public static final String CODE = "510";
	
	public String getDescription() {
		return "Outputed when the TPI has detected a change of state in the Partition 1 keypad LEDs.";
	}
	
	@Override
	public String toString() {
		return "EnvisalinkLEDStateCommand [getCommandCode()=" + getCommandCode() + ", getData()=" + getData()
				+ ", getActiveLeds()=" + Arrays.toString(getActiveLeds()) + "]";
	}
	
	
	
}
