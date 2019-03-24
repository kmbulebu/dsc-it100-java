package com.github.kmbulebu.dsc.it100.commands.read;

public enum Led {
	
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
	
	private Led(int number, String name) {
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