package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.Date;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.CommandTimeDateFormat;

public class TimeDateBroadcastCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4496578453948452873L;

	public static final String CODE = "550";
	
	private String dateTimeString = null;
	private Date date = null;


	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 10) {
			throw new CommandDataParseException("Expected data length of 10 bytes, received " + dataString.length());
		}
		dateTimeString = new String(dataString);
		
		date = CommandTimeDateFormat.parseString(dateTimeString);
	}
	
	public String getDateTimeString() {
		return dateTimeString;
	}
	
	public Date getDateTime() {
		return date;
	}

	public String getDescription() {
		return "The IT-100 transmits system time broadcasts at 4 minute intervals in response to the following application command.";
	}

	@Override
	public String toString() {
		return "TimeDateBroadcastCommand [getDateTime()=" + getDateTime()
				+ ", toString()=" + super.toString() + "]";
	}
	

	
	
}
