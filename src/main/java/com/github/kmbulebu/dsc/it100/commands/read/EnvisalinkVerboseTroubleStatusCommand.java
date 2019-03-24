package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnvisalinkVerboseTroubleStatusCommand extends ReadCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -73942710757399136L;
	
	public static final String CODE = "849";
	
	protected Trouble[] activeTroubles;

	public EnvisalinkVerboseTroubleStatusCommand() {
		super();
	}

	protected Trouble[] parseTroubleStatus(String chars) {
		
		int troubleBitMask;
		try {
			troubleBitMask = Integer.parseInt(chars, 16);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Trouble status bitmask was not a valid number: " + e.getMessage(), e);
		}
		
		final List<Trouble> onTroubles = new ArrayList<Trouble>();
		
		for (Trouble troubleValue : Trouble.values()) {
			if ((troubleBitMask & (1 << troubleValue.getBitNumber())) > 0) {
				onTroubles.add(troubleValue);
			}
		}
		
		Collections.sort(onTroubles);
		return onTroubles.toArray(new Trouble[onTroubles.size()]);
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException("Expected data length of 2 bytes, received " + dataString.length());
		}
		
		this.activeTroubles = parseTroubleStatus(dataString);
	}

	public Trouble[] getTroubles() {
		return activeTroubles;
	}
	
	public String getDescription() {
		return "This command is issued when a trouble appears on the system and roughly every 5 minutes until the trouble is cleared. The two characters are a bitfield (similar to 510,511). The meaning of each bit is the same as what you see on an LED keypad (see the user manual).";
	}
	
	@Override
	public String toString() {
		return "EnvisalinkVerboseTroubleStatusCommand [getCommandCode()=" + getCommandCode() + ", getData()="
				+ getData() + ", getTroubles()=" + Arrays.toString(getTroubles()) + "]";
	}
	
	public enum Trouble {
		
		SERVICE_REQUIRED(0, "Service is Required"),
		AC_POWER_LOST(1, "AC Power Lost"),
		TELEPHONE_LINE_FAULT(2, "Telephone Line Fault"),
		FAILURE_TO_COMMUNICATE(3, "Failure to Communicate"),
		ZONE_FAULT(4, "Sensor/Zone Fault"),
		ZONE_TAMPER(5, "Sensor/Zone Tamper"),
		ZONE_LOW_BATTERY(6, "Sensor/Zone Low Battery"),
		TIME_LOST(7, "Loss of Time");
		
		private final int bitNumber;
		private final String description;
		
		private Trouble(int bitNumber, String description) {
			this.bitNumber = bitNumber;
			this.description = description;
		}

		public int getBitNumber() {
			return bitNumber;
		}

		public String getDescription() {
			return description;
		}
		
	}

}