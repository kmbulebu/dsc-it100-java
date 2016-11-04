package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class BuzzerStatusCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CODE = "906";
	private int duration;

	@Override
	public String getDescription() {
		return "The IT-100 sends Buzzer Status";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 3) {
			throw new CommandDataParseException("Expected data length 3 bytes, received " + dataString.length());
		}
		duration = Integer.parseInt(dataString);
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "BuzzerStatusCommand [toString()=" + super.toString() + "]";
	}
}
