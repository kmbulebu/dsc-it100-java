package com.github.kmbulebu.dsc.it100.commands.read;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BasePartitionCommandOutputCommandTest {
	
	private EnvisalinkCommandOutputPressedCommand command;
	
	@Before
	public void setup() {
		command = new EnvisalinkCommandOutputPressedCommand();
	}

	@Test
	public void testParseData() {
		command.parseData("11");
		assertEquals(1, command.getPartition());
		assertEquals(1, command.getCommand());
		
		command.parseData("14");
		assertEquals(1, command.getPartition());
		assertEquals(4, command.getCommand());
		
		command.parseData("23");
		assertEquals(2, command.getPartition());
		assertEquals(3, command.getCommand());
	}

}
