package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class EnvisalinkLEDStateCommand extends ReadCommand implements ICommandHelp {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1986910085261715304L;

	public static final String CODE = "510";
	
	private LED[] onLeds;

	public String getDescription() {
		return "Outputed when the TPI has detected a change of state in the Partition 1 keypad LEDs.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException("Expected data length of 2 bytes, received " + dataString.length());
		}
		final String ledBitMaskString = dataString.substring(0, 1);
		
		int ledBitMask;
		try {
			ledBitMask = Integer.parseInt(ledBitMaskString, 16);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("LED status bitmask was not a valid number: " + e.getMessage(), e);
		}
		
		final List<LED> onLeds = new ArrayList<LED>();
		
		// Bit 7 – BACKLIGHT LED
		if ((ledBitMask & (1 << 7)) > 0) {
			onLeds.add(LED.BACKLIGHT);
		}
		// Bit 6 - FIRE LED
		if ((ledBitMask & (1 << 6)) > 0) {
			onLeds.add(LED.FIRE);
		}
		// Bit 5 - PROGRAM LED
		if ((ledBitMask & (1 << 5)) > 0) {
			onLeds.add(LED.PROGRAM);
		}
		// Bit 4 - TROUBLE LED
		if ((ledBitMask & (1 << 4)) > 0) {
			onLeds.add(LED.TROUBLE);
		}
		// Bit 3 - BYPASS LED
		if ((ledBitMask & (1 << 3)) > 0) {
			onLeds.add(LED.BYPASS);
		}
		// Bit 2 - MEMORY LED
		if ((ledBitMask & (1 << 2)) > 0) {
			onLeds.add(LED.MEMORY);
		}
		// Bit 1 - ARMED; LED 
		if ((ledBitMask & (1 << 1)) > 0) {
			onLeds.add(LED.MEMORY);
		}
		// Bit 0 - READY LED
		if ((ledBitMask & (1 << 0)) > 0) {
			onLeds.add(LED.READY);
		}
		Collections.sort(onLeds);
		this.onLeds = onLeds.toArray(new LED[onLeds.size()]);
	}

	public LED[] getOnLeds() {
		return onLeds;
	}

	@Override
	public String toString() {
		return "EnvisalinkLEDStateCommand [onLeds=" + onLeds + "]";
	}

	public enum LED {
		
		READY(1, "Ready"),
		ARMED(2, "Armed"),
		MEMORY(3, "Memory"),
		BYPASS(4, "Bypass"),
		TROUBLE(5, "Trouble"),
		PROGRAM(6, "Program"),
		FIRE(7, "Fire"),
		BACKLIGHT(8, "Backlight"),
		AC(9, "AC");
		
		private final int number;
		private final String name;
		
		private LED(int number, String name) {
			this.number = number;
			this.name = name;
		}

		public int getNumber() {
			return number;
		}

		public String getName() {
			return name;
		}
		
		
	}
	
	
	
}
