package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public abstract class BasePartitionWriteCommand extends WriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	private final int partition;

	public BasePartitionWriteCommand(String code, int partition) {
		super(code, Integer.toString(partition));
		this.partition = partition;
	}
	
	public BasePartitionWriteCommand(String code, int partition, String suffixData) {
		super(code, Integer.toString(partition) + suffixData);
		this.partition = partition;
	}

	public int getPartition() {
		return partition;
	}

}
