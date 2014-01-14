package com.oakcity.dsc.it100.commands.util;

import java.util.Date;

public class DataStringBuilder {
	
	private StringBuilder data = new StringBuilder();
	
	public String build() {
		final String result = data.toString();
		data.setLength(0);
		return result;
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
	
	public DataStringBuilder appendIntData(int value, int length) {
		this.data.append(intToString(value, length));
		return this;
	}
	
	public DataStringBuilder appendStringData(String data) {
		this.data.append(data);
		return this;
	}
	
	public DataStringBuilder appendBoolean(boolean on) {
		if (on) {
			this.data.append('1');
		} else {
			this.data.append('0');
		}
		return this;
	}
	
	public DataStringBuilder appendDate(Date date) {
		CommandTimeDateFormat format = new CommandTimeDateFormat();
		this.data.append(format.format(date));
		return this;
	}
	
	public DataStringBuilder appendPinCode(String code) {
		StringBuilder builder = new StringBuilder();
		builder.append(code);
		
		for (int i = 0; i < (6-code.length()); i++) {
			builder.append('0');
		}
		this.data.append(builder);
		return this;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	

}
