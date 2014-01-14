package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionNotReadyCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {
	
	public static final String CODE = "651";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968618089582632808L;
	public String getDescription() {
		return "This command indicates that the partition cannot be armed (zones open, trouble present, etc).";
	}
	
	@Override
	public String toString() {
		return "PartitionNotReadyCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setReady(false);
	}

}
