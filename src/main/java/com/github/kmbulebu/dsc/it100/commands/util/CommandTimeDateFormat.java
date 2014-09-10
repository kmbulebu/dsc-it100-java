package com.github.kmbulebu.dsc.it100.commands.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand.CommandDataParseException;

public class CommandTimeDateFormat extends SimpleDateFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3188960140386991394L;

	public static final String DATE_FORMAT = "HHmmMMddyy"; // hhmmMMDDYY

	public CommandTimeDateFormat() {
		super(DATE_FORMAT);
	}
	
	public static Date parseString(String timeDateString) throws CommandDataParseException  {
		final CommandTimeDateFormat format = new CommandTimeDateFormat();
		try {
			return format.parse(timeDateString);
		} catch (ParseException e) {
			throw new CommandDataParseException(e);
		}
	}
	
	
}
