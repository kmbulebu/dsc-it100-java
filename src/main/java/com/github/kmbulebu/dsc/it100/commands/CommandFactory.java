package com.github.kmbulebu.dsc.it100.commands;

import java.util.Date;

import com.github.kmbulebu.dsc.it100.commands.util.CommandTimeDateFormat;

public class CommandFactory {
	
	String commandCode = null;
	StringBuilder data = new StringBuilder();
	
	public ICommand build() {
		ICommand command = build(commandCode, data.toString());
		commandCode = null;
		data.setLength(0);
		return command;
	}
	
	private ICommand build(String commandCode, String data) {
		final String finalCommandCode = new String(commandCode);
		final String finalData = new String(data);
		return new ICommand() {

			public String getCommandCode() {
				return finalCommandCode;
			}

			public String getData() {
				return finalData;
			}
			
		};
	}
	
	public void setCommandCode(String commandCode) {
		if (commandCode.length() != 3) {
			throw new IllegalArgumentException("commandCode must have length of 3 characters.");
		}
		this.commandCode = commandCode;
	}
	
	public void setCommandCode(int commandCode) {
		if (commandCode > 999 || commandCode < 0) {
			throw new IllegalArgumentException("commandCode must be greater than 0 and less than 1000, at most 3 characters.");
		}
		setCommandCode(intToString(commandCode, 3));
	}
	
	private String intToString(int value, int stringLength) {
		String intString = Integer.toString(value);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < (stringLength-intString.length()); i++) {
			builder.append('0');

		}
		builder.append(intString);
		return builder.toString();
	}
	
	public void appendIntData(int value, int length) {
		this.data.append(intToString(value, length));
	}
	
	public void appendStringData(String data) {
		this.data.append(data);
	}
	
	public void appendBoolean(boolean on) {
		if (on) {
			this.data.append('1');
		} else {
			this.data.append('0');
		}
	}
	
	public void appendDate(Date date) {
		CommandTimeDateFormat format = new CommandTimeDateFormat();
		this.data.append(format.format(date));
	}
	

}
