package com.github.kmbulebu.dsc.it100.commands.read;

import java.util.Date;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.CommandTimeDateFormat;

public class RingDetectedCommand extends ReadCommand implements ICommandHelp {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3916377545304734967L;


	public static final String CODE = "560";
	
	
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
		return "This command indicates that the panel has detected a ring on the telephone line. NOTE: An ESCORT 5580TC module is required to receive this command.";
	}

	@Override
	public String toString() {
		return "RingDetectedCommand [getDateTime()=" + getDateTime()
				+ ", toString()=" + super.toString() + "]";
	}
	

	
	
}
