package com.oakcity.dsc.it100.commands.write;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionArmAwayCommand extends BasePartitionWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "030";

	public PartitionArmAwayCommand(int partition) {
		super(CODE, partition);
	}

	public String getDescription() {
		return "Arms selected partition in AWAY mode (no zones bypassed).";
	}

}
