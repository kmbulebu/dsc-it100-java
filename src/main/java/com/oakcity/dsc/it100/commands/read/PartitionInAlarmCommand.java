package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionInAlarmCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3773126575467740813L;
	public static final String CODE = "654";

	public String getDescription() {
		return "This command indicates that a partition is in alarm.";
	}

	@Override
	public String toString() {
		return "PartitionInAlarmCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setInAlarm(true);
		partition.setInEntryDelay(false);
	}

}
