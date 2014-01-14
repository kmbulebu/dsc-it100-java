package com.oakcity.dsc.it100.commands.read;

import java.util.Arrays;

import com.oakcity.dsc.it100.IVirtualKeypad;
import com.oakcity.dsc.it100.IVirtualKeypadStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class LCDUpdateCommand extends ReadCommand implements ICommandHelp, IVirtualKeypadStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -94076149793102204L;
	public static final String CODE = "901";
	
	private int lineNumber;
	private int columnNumber;
	private int numberCharactersToDisplay;
	private char[] asciiData;

	public String getDescription() {
		return "The IT-100 sends this command whenever the text of the IT-100 menu changes";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() < 6 || dataString.length() > 37) {
			throw new CommandDataParseException("Expected data length between 6 and 37 bytes, received " + dataString.length());
		}
		final String lineNumberString = dataString.substring(0, 1);
		
		try {
			lineNumber = Integer.parseInt(lineNumberString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Line number was not a valid number: " + e.getMessage(), e);
		}
		
		if (lineNumber != 0 && lineNumber != 1) {
			throw new CommandDataParseException("Line number was not valid. Expected 0 or 1, received " + lineNumber);
		}
		
		final String columnNumberString = dataString.substring(1, 3);
		try {
			columnNumber = Integer.parseInt(columnNumberString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Column number was not a valid number: " + e.getMessage(), e);
		}
		
		if (columnNumber < 0 || columnNumber > 15) {
			throw new CommandDataParseException("Column number was not valid. Expected between 0 and  15, received " + columnNumber);
		}
		
		final String numberCharactersToDisplayString = dataString.substring(3, 5);
		try {
			numberCharactersToDisplay = Integer.parseInt(numberCharactersToDisplayString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Number of characters to display was not a valid number: " + e.getMessage(), e);
		}
		
		if (numberCharactersToDisplay < 1 || numberCharactersToDisplay > 32) {
			throw new CommandDataParseException("Number of characters to display was not valid. Expected between 1 and  32, received " + numberCharactersToDisplay);
		}
		
		final String asciiDataString = dataString.substring(5);
		if (asciiDataString.length() != numberCharactersToDisplay) {
			throw new CommandDataParseException("Number of characters to display did not match ascii data length. " + numberCharactersToDisplay + " != " + asciiDataString.length());
		}
		
		asciiData = dataString.substring(5, 5 + numberCharactersToDisplay).toCharArray();
	}

	@Override
	public boolean isVirtualKeypadChanged() {
		return true;
	}

	@Override
	public void updateVirtualKeypad(IVirtualKeypad keypad) {
		int startingPos = lineNumber*16 + columnNumber;
		
		
		String original = keypad.getLcdLine1() + keypad.getLcdLine2();
		if (original.length() != 32) {
			original = "                                ";
		}
		char[] lineChars = original.toCharArray();
		
		for (int i = 0; i < asciiData.length; i++ ) {
			lineChars[i + startingPos] = asciiData[i];
		}
		
		keypad.setLcdLine1(new String(lineChars, 0, 16));
		keypad.setLcdLine2(new String(lineChars, 16, 16));
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public int getNumberCharactersToDisplay() {
		return numberCharactersToDisplay;
	}

	public char[] getAsciiData() {
		return Arrays.copyOf(asciiData, asciiData.length);
	}

	@Override
	public String toString() {
		int[] charArray = new int[asciiData.length];
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (int) asciiData[i];
		}
		return "LCDUpdateCommand [lineNumber=" + lineNumber + ", columnNumber=" + columnNumber
				+ ", numberCharactersToDisplay=" + numberCharactersToDisplay + ", asciiData="
				+ Arrays.toString(asciiData) + ", asciiDataDec=" + Arrays.toString(charArray) + "]";
	}

	

	
	
	
}
