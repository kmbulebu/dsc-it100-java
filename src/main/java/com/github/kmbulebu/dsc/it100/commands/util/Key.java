package com.github.kmbulebu.dsc.it100.commands.util;

public enum Key {

	//Numerical Keypad
	ZERO('0'), ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'),
	ASTERISK('*'), POUND('#'),
	//Fire, Ambulance, Panic keys
	FIRE('F'), AMBULANCE('A'), PANIC('P'),
	//Function Keys 1- 5
	a('a'), b('b'), c('c'), d('d'), e('e'),
	//Arrow Keys
	LEFT('<'), RIGHT('>'),
	//Both Arrow Keys <>
	BOTH('='),
	//Break Key
	BREAK('^');

	private char asciiChar;

	Key(char asciiChar) {
		this.asciiChar = asciiChar;
	}

	public char getAsciiChar() {
		return asciiChar;
	}

	public static Key fromAsciiChar(char c) {
		for (Key k : Key.values()) {
			if (k.asciiChar == c) {
				return k;
			}
		}
		return null;
	}
}
