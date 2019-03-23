package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.Arrays;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class EnvisalinkLEDFlashStateCommand extends BaseEnvisalinkLEDCommand implements ICommandHelp {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5383744689752748856L;

	public static final String CODE = "511";

	public String getDescription() {
		return "Outputed when the TPI has detected a change of state in the Partition 1 keypad LEDs as to whether to flash or not. Overrides 510. That is, if 511 says the PROGRAM LED is flashing, then it doesn't matter what 510 says.";
	}

	@Override
	public String toString() {
		return "EnvisalinkLEDFlashStateCommand [getCommandCode()=" + getCommandCode() + ", getData()=" + getData()
				+ ", getActiveLeds()=" + Arrays.toString(getActiveLeds()) + "]";
	}
}
