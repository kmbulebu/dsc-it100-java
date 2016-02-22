package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.Key;

public class KeyPressCommand extends WriteCommand implements ICommandHelp {

	public static final String CODE = "070";

	public KeyPressCommand(Key key) {
		super(CODE, Character.toString(key.getAsciiChar()));
	}

	@Override
	public String getDescription() {
		return "This command simulates a Keypress on a Keypad. Keypress must be followed by a keybreak.";
	}

	@Override
	public String toString() {
		return "KeyPressCommand [data()=" + super.getData() + "]";
	}
}