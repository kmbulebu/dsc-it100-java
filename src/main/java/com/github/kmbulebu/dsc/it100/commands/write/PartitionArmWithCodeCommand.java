package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class PartitionArmWithCodeCommand extends BasePartitionPlusCodeWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "033";

	public PartitionArmWithCodeCommand(int partition, String code) {
		super(CODE, partition, code);
	}

	public String getDescription() {
		return "Requires a user code to arm the selected partition. This is identical to entering an access code when a partition is in Ready mode.";
	}

}
