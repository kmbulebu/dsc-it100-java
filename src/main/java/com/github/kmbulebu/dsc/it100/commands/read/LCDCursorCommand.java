package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class LCDCursorCommand extends ReadCommand implements ICommandHelp {

	public static final String CODE = "902";

	private CursorType cursorType;
	private int lineNumber;
	private int columnNumber;

	public CursorType getCursorType() {
		return cursorType;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public String getDescription() {
		return "The IT-100 sends this command whenever the cursor position changes";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 4) {
			throw new CommandDataParseException("Expected data length 4 bytes, received " + dataString.length());
		}

		try {
			Integer cursorType = Integer.parseInt(dataString.substring(0, 1));
			this.cursorType = CursorType.values()[cursorType];
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Cursor type was not a valid number: " + e.getMessage(), e);
		}

		final String lineNumberString = dataString.substring(1, 2);

		try {
			lineNumber = Integer.parseInt(lineNumberString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Line number was not a valid number: " + e.getMessage(), e);
		}

		if (lineNumber != 0 && lineNumber != 1) {
			throw new CommandDataParseException("Line number was not valid. Expected 0 or 1, received " + lineNumber);
		}

		final String columnNumberString = dataString.substring(2, 5);
		try {
			columnNumber = Integer.parseInt(columnNumberString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Column number was not a valid number: " + e.getMessage(), e);
		}

		if (columnNumber < 0 || columnNumber > 15) {
			throw new CommandDataParseException("Column number was not valid. Expected between 0 and  15, received " + columnNumber);
		}
	}

	public enum CursorType {
		OFF,
		UNDERSCORE,
		BLOCK
	}
}
