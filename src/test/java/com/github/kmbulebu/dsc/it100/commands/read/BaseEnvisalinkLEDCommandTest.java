package com.github.kmbulebu.dsc.it100.commands.read;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BaseEnvisalinkLEDCommandTest {
	
	private static final int READY_BIT         = 0b00000001;
	private static final int ARMED_BIT         = 0b00000010;
	private static final int MEMORY_BIT        = 0b00000100;
	private static final int BYPASS_BIT        = 0b00001000;
	private static final int TROUBLE_BIT       = 0b00010000;
	private static final int PROGRAM_BIT       = 0b00100000;
	private static final int FIRE_BIT          = 0b01000000;
	private static final int BACKLIGHT_BIT     = 0b10000000;
	
	private BaseEnvisalinkLEDCommand command;
	
	@Before
	public void setup() {
		command = new BaseEnvisalinkLEDCommand() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getDescription() {
				return null;
			}
			
		};
	}
	
	private static String leftPadToString(int number) {
	    return String.format("%02x", number);  
	}
	
	@Test
	public void testParseData() {
		Led[] leds;
		
		command.parseData(leftPadToString(READY_BIT));
		leds = command.getActiveLeds();
		assertEquals(1, leds.length);
		assertEquals(Led.READY, leds[0]);
		
		command.parseData(leftPadToString(BACKLIGHT_BIT));
		leds = command.getActiveLeds();
		assertEquals(1, leds.length);
		assertEquals(Led.BACKLIGHT, leds[0]);
		
		command.parseData(leftPadToString(TROUBLE_BIT | BACKLIGHT_BIT));
		leds = command.getActiveLeds();
		assertEquals(2, leds.length);
		assertEquals(Led.TROUBLE, leds[0]);
		assertEquals(Led.BACKLIGHT, leds[1]);
	}

	@Test
	public void testParseLedStatusReady() {
		final Led[] leds = command.parseLedStatus(leftPadToString(READY_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.READY, leds[0]);
	}
	
	@Test
	public void testParseLedStatusArmed() {
		final Led[] leds = command.parseLedStatus(leftPadToString(ARMED_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.ARMED, leds[0]);
	}
	
	@Test
	public void testParseLedStatusMemory() {
		final Led[] leds = command.parseLedStatus(leftPadToString(MEMORY_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.MEMORY, leds[0]);
	}
	
	@Test
	public void testParseLedStatusTrouble() {
		final Led[] leds = command.parseLedStatus(leftPadToString(TROUBLE_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.TROUBLE, leds[0]);
	}
	
	@Test
	public void testParseLedStatusBypass() {
		final Led[] leds = command.parseLedStatus(leftPadToString(BYPASS_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.BYPASS, leds[0]);
	}
	
	@Test
	public void testParseLedStatusProgram() {
		final Led[] leds = command.parseLedStatus(leftPadToString(PROGRAM_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.PROGRAM, leds[0]);
	}
	
	@Test
	public void testParseLedStatusFire() {
		final Led[] leds = command.parseLedStatus(leftPadToString(FIRE_BIT));
		
		assertEquals(1, leds.length);
		assertEquals(Led.FIRE, leds[0]);
	}
	
	@Test
	public void testParseLedStatusBacklight() {
		final Led[] leds = command.parseLedStatus(leftPadToString(BACKLIGHT_BIT));
		assertEquals(1, leds.length);
		assertEquals(Led.BACKLIGHT, leds[0]);
	}
	
	@Test
	public void testParseLedStatusBacklightAndTrouble() {
		// Captured from an actual panel
		final Led[] leds = command.parseLedStatus("90");
		assertEquals(2, leds.length);
		assertEquals(Led.TROUBLE, leds[0]);
		assertEquals(Led.BACKLIGHT, leds[1]);
	}
	
	

}
