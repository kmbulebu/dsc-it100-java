package com.github.kmbulebu.dsc.it100.commands;

import org.junit.Test;

import com.github.kmbulebu.dsc.it100.commands.util.CommandChecksum;

import junit.framework.TestCase;

public class CommandTest extends TestCase {
	
	@Test
	public void testVerifyChecksum() {
		assertTrue(CommandChecksum.verifyChecksum("654", "3", "D2"));
	}
	
	@Test
	public void testCalculateChecksum() {
		assertEquals("D2", CommandChecksum.calculateChecksum("654", "3"));
	}

}
