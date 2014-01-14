package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class CommandOutputInProgressCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120619474389903464L;
	public static final String CODE = "660";

	public String getDescription() {
		return "This command indicates that a partition is in command output mode of operation.";
	}

	@Override
	public String toString() {
		return "CommandOutputInProgressCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setIsInCommandOutput(true);
	}

}
