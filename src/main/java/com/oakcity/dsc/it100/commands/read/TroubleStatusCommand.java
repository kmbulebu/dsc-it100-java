package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class TroubleStatusCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708765578935248460L;
	
	public static final String CODE = "840";

	public String getDescription() {
		return "This command sends the general trouble status that the trouble LED on a keypad normally displays when there is a trouble present on system.";
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setInTrouble(true);
	}

	@Override
	public String toString() {
		return "TroubleStatusCommand [toString()=" + super.toString() + "]";
	}
	
	

}
