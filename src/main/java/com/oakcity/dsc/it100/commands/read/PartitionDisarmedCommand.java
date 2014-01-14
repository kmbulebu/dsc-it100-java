package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionDisarmedCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3816440777910452558L;
	public static final String CODE = "655";

	public String getDescription() {
		return "This command indicates that a partition has been disarmed.";
	}

	@Override
	public String toString() {
		return "PartitionDisarmedCommand [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setArmed(false);
		partition.setArmedMode(null);
		partition.setInEntryDelay(false);
		partition.setInAlarm(false);
	}

}
