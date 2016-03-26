package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class ToneStatusCommand extends ReadCommand implements ICommandHelp {

	public static final String CODE = "905";

	private boolean constantToneControl;
	private int numberOfBeeps;
	private int interval;

	public boolean isConstantToneControl() {
		return constantToneControl;
	}

	public int getInterval() {
		return interval;
	}

	public int getNumberOfBeeps() {
		return numberOfBeeps;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 4) {
			throw new CommandDataParseException("Expected data length 4 bytes, received " + dataString.length());
		}

		String constantToneControlString = dataString.substring(0, 1);

		try {
			Integer constantToneControl = Integer.valueOf(constantToneControlString);
			this.constantToneControl = constantToneControl == 1;
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Constant tone control was not a valid number: " + e.getMessage(), e);
		}

		final String numberOfBeepsString = dataString.substring(1, 2);
		try {
			numberOfBeeps = Integer.parseInt(numberOfBeepsString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Number of beeps was not a valid number: " + e.getMessage(), e);
		}

		if (numberOfBeeps < 0 || numberOfBeeps > 7) {
			throw new CommandDataParseException("Number of beeps was not valid. Expected between 0 and  7, received " + numberOfBeeps);
		}

		final String intervalString = dataString.substring(2, 4);
		try {
			interval = Integer.parseInt(intervalString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Interval was not a valid number: " + e.getMessage(), e);
		}

		if (interval < 0 || interval > 15) {
			throw new CommandDataParseException("Interval was not valid. Expected between 0 and  15, received " + interval);
		}
	}
}
