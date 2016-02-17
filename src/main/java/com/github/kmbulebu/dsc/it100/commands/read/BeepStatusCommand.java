package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class BeepStatusCommand extends ReadCommand implements ICommandHelp {

	public static final String CODE = "904";
	private int duration;

	@Override
	public String getDescription() {
		return "The IT-100 sends Beep Status to the application";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		duration = Integer.parseInt(dataString);
	}

	public int getDuration() {
		return duration;
	}
}
