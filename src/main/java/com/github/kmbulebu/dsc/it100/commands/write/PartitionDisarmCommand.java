package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PartitionDisarmCommand extends BasePartitionPlusCodeWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "040";

	public PartitionDisarmCommand(int partition, String code) {
		super(CODE, partition, code);
	}

	public String getDescription() {
		return "Disarms the selected partition. Sending the Partition Disarm command will silence any alarms as well as disarm the partition.";
	}

}
