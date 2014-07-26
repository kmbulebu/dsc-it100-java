package com.oakcity.dsc.it100.commands.write;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public abstract class BasePartitionPlusCodeWriteCommand extends BasePartitionWriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;

	public BasePartitionPlusCodeWriteCommand(String commandCode, int partition, String userCode) {
		super(commandCode, partition, paddedCode(userCode));
	}
	
	private static String paddedCode(String code) {
		if (code.length() == 6) {
			return code;
		} else {
			return code + "00";
		}
	}


}
