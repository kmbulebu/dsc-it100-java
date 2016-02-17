package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class BuzzerStatusCommand extends ReadCommand implements ICommandHelp {

	public static final String CODE = "905";
	private int duration;

	@Override
	public String getDescription() {
		return "The IT-100 sends Buzzer Status";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
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
