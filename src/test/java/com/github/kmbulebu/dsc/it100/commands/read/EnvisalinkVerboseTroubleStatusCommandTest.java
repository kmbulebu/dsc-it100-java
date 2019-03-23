package com.github.kmbulebu.dsc.it100.commands.read;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.kmbulebu.dsc.it100.commands.read.EnvisalinkVerboseTroubleStatusCommand.Trouble;

public class EnvisalinkVerboseTroubleStatusCommandTest {
	
	private EnvisalinkVerboseTroubleStatusCommand command;
	
	// Two ascii chars, one hex byte. Bitmask of multiple possible troubles.
	//		bit 0 = Service is Required
	//		bit 1 = AC Power Lost
	//		bit 2 = Telephone Line Fault 
	//		bit 3 = Failure to Communicate 
	//		bit 4 = Sensor/Zone Fault
	//		bit 5 = Sensor/Zone Tamper
	//		bit 6 = Sensor/Zone Low Battery bit 7 = Loss of Time
	private static final int SERVICE_REQUIRED_BIT		= 0b00000001;
	private static final int AC_POWER_LOST_BIT			= 0b00000010;
	private static final int TELEPHONE_LINE_FAULT_BIT	= 0b00000100;
	private static final int FAILURE_TO_COMMUNICATE_BIT	= 0b00001000;
	private static final int ZONE_FAULT_BIT				= 0b00010000;
	private static final int ZONE_TAMPER_BIT			= 0b00100000;
	private static final int ZONE_LOW_BATTERY_BIT		= 0b01000000;
	
	@Before
	public void setup() {
		command = new EnvisalinkVerboseTroubleStatusCommand();
	}
	
	@Test
	public void testTroubleEnumValues() {
		// Testing these allows us to reuse their values in other tests.
		assertEquals(0, Trouble.SERVICE_REQUIRED.getBitNumber());
		assertEquals(1, Trouble.AC_POWER_LOST.getBitNumber());
		assertEquals(2, Trouble.TELEPHONE_LINE_FAULT.getBitNumber());
		assertEquals(3, Trouble.FAILURE_TO_COMMUNICATE.getBitNumber());
		assertEquals(4, Trouble.ZONE_FAULT.getBitNumber());
		assertEquals(5, Trouble.ZONE_TAMPER.getBitNumber());
		assertEquals(6, Trouble.ZONE_LOW_BATTERY.getBitNumber());
	}

	@Test
	public void testParseData() {
		Trouble[] troubles;

		command.parseData(leftPadToString(SERVICE_REQUIRED_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.SERVICE_REQUIRED, troubles[0]);
		
		command.parseData(leftPadToString(AC_POWER_LOST_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.AC_POWER_LOST, troubles[0]);
		
		command.parseData(leftPadToString(TELEPHONE_LINE_FAULT_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.TELEPHONE_LINE_FAULT, troubles[0]);
		
		command.parseData(leftPadToString(FAILURE_TO_COMMUNICATE_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.FAILURE_TO_COMMUNICATE, troubles[0]);
		
		command.parseData(leftPadToString(ZONE_FAULT_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.ZONE_FAULT, troubles[0]);
		
		command.parseData(leftPadToString(ZONE_TAMPER_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.ZONE_TAMPER, troubles[0]);
		
		command.parseData(leftPadToString(ZONE_LOW_BATTERY_BIT));
		troubles = command.getTroubles();
		assertEquals(1, troubles.length);
		assertEquals(Trouble.ZONE_LOW_BATTERY, troubles[0]);
		
		command.parseData(leftPadToString(ZONE_LOW_BATTERY_BIT | TELEPHONE_LINE_FAULT_BIT | SERVICE_REQUIRED_BIT));
		troubles = command.getTroubles();
		assertEquals(3, troubles.length);
		assertEquals(Trouble.SERVICE_REQUIRED, troubles[0]);
		assertEquals(Trouble.TELEPHONE_LINE_FAULT, troubles[1]);
		assertEquals(Trouble.ZONE_LOW_BATTERY, troubles[2]);
	}
	
	private static String leftPadToString(int number) {
	    return String.format("%02x", number);  
	}

}
