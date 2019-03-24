package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class LEDStatusCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -94076149793102204L;
	public static final String CODE = "903";
	
	private Led led;
	private LEDStatus ledStatus;

	public String getDescription() {
		return "Updates the Virtual Keypads LED indicators.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException("Expected data length of 2 bytes, received " + dataString.length());
		}
		final String ledNumberString = dataString.substring(0, 1);
		
		int ledNumber;
		try {
			ledNumber = Integer.parseInt(ledNumberString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("LED number was not a valid number: " + e.getMessage(), e);
		}
		
		if (ledNumber < 0 && ledNumber > 9) {
			throw new CommandDataParseException("LED number was not valid. Expected between 1 and 9, received " + ledNumber);
		}
		
		switch (ledNumber) {
			case(1): led = Led.READY; break;
			case(2): led = Led.ARMED; break;
			case(3): led = Led.MEMORY; break;
			case(4): led = Led.BYPASS; break;
			case(5): led = Led.TROUBLE; break;
			case(6): led = Led.PROGRAM; break;
			case(7): led = Led.FIRE; break;
			case(8): led = Led.BACKLIGHT; break;
			case(9): led = Led.AC; break;
		}
		
		
		final String ledStatusString = dataString.substring(1, 2);
		int ledStatusInt;
		try {
			ledStatusInt = Integer.parseInt(ledStatusString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("LED Status was not a valid number: " + e.getMessage(), e);
		}
		
		if (ledStatusInt < 0 || ledStatusInt > 2) {
			throw new CommandDataParseException("LED Status was not valid. Expected between 0 and  2, received " + ledStatusInt);
		}
		
		switch (ledStatusInt) {
			case(0): ledStatus = LEDStatus.OFF; break;
			case(1): ledStatus = LEDStatus.ON; break;
			case(2): ledStatus = LEDStatus.FLASHING; break;
		}

	}

	public Led getLed() {
		return led;
	}

	public LEDStatus getLedStatus() {
		return ledStatus;
	}

	@Override
	public String toString() {
		return "LEDStatusCommand [led=" + led + ", ledStatus=" + ledStatus + "]";
	}

	public enum LEDStatus {
		OFF(0),
		ON(1),
		FLASHING(2);
		
		private final int code;
		
		private LEDStatus(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	
	
}
