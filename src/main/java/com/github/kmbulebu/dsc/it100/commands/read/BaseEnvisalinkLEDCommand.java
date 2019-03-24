package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseEnvisalinkLEDCommand extends ReadCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -73942710757399136L;
	protected Led[] activeLeds;

	public BaseEnvisalinkLEDCommand() {
		super();
	}

	protected Led[] parseLedStatus(String chars) {
		
		int ledBitMask;
		try {
			ledBitMask = Integer.parseInt(chars, 16);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("LED status bitmask was not a valid number: " + e.getMessage(), e);
		}
		
		final List<Led> onLeds = new ArrayList<Led>();
		
		// Bit 7 – BACKLIGHT LED
		if ((ledBitMask & (1 << 7)) > 0) {
			onLeds.add(Led.BACKLIGHT);
		}
		// Bit 6 - FIRE LED
		if ((ledBitMask & (1 << 6)) > 0) {
			onLeds.add(Led.FIRE);
		}
		// Bit 5 - PROGRAM LED
		if ((ledBitMask & (1 << 5)) > 0) {
			onLeds.add(Led.PROGRAM);
		}
		// Bit 4 - TROUBLE LED
		if ((ledBitMask & (1 << 4)) > 0) {
			onLeds.add(Led.TROUBLE);
		}
		// Bit 3 - BYPASS LED
		if ((ledBitMask & (1 << 3)) > 0) {
			onLeds.add(Led.BYPASS);
		}
		// Bit 2 - MEMORY LED
		if ((ledBitMask & (1 << 2)) > 0) {
			onLeds.add(Led.MEMORY);
		}
		// Bit 1 - ARMED; LED 
		if ((ledBitMask & (1 << 1)) > 0) {
			onLeds.add(Led.ARMED);
		}
		// Bit 0 - READY LED
		if ((ledBitMask & (1 << 0)) > 0) {
			onLeds.add(Led.READY);
		}
		Collections.sort(onLeds);
		return onLeds.toArray(new Led[onLeds.size()]);
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException("Expected data length of 2 bytes, received " + dataString.length());
		}
		
		this.activeLeds = parseLedStatus(dataString);
	}

	public Led[] getActiveLeds() {
		return activeLeds;
	}

}