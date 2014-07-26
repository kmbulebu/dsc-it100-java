package com.oakcity.dsc.it100.commands.write;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionArmedNoEntryDelayCommand extends BasePartitionWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "032";

	public PartitionArmedNoEntryDelayCommand(int partition) {
		super(CODE, partition);
	}

	public String getDescription() {
		return "Arms selected partition with NO entry delay.";
	}

}
