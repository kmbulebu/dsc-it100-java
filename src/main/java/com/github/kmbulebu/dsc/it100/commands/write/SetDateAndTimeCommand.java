package com.github.kmbulebu.dsc.it100.commands.write;

import java.util.Date;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.DataStringBuilder;

public class SetDateAndTimeCommand extends WriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "010";
	
	private final Date date;

	public SetDateAndTimeCommand(Date date) {
		super(CODE, new DataStringBuilder().appendDate(date).build());
		this.date = date;
	}


	public String getDescription() {
		return "Sets new Time and Date on the Alarm System. The IT-100 responds with: *Code Required (900) *Dependent on Alarm System programming.";
	}


	public Date getDate() {
		return date;
	}
	
	


}
