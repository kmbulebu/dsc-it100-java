package com.oakcity.dsc.it100.commands.write;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionArmStayCommand extends BasePartitionWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "031";

	public PartitionArmStayCommand(int partition) {
		super(CODE, partition);
	}

	public String getDescription() {
		return "rms the selected partition in STAY-ARM mode.";
	}

}
