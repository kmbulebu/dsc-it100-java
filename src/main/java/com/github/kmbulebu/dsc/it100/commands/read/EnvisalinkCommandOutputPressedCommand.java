package com.github.kmbulebu.dsc.it100.commands.read;

public class EnvisalinkCommandOutputPressedCommand extends BasePartitionCommandOutputCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -73942710757399136L;
	
	public static final String CODE = "912";

	public EnvisalinkCommandOutputPressedCommand() {
		super();
	}

	public String getDescription() {
		return "This command will tell the API to enter an access code. Once entered, the 200 command will be sent to perform the required action. The code should be entered within the window time of the panel.";
	}
	
	@Override
	public String toString() {
		return "EnvisalinkCommandOutputPressedCommand [getCommandCode()=" + getCommandCode() + ", getData()="
				+ getData() + ", getPartition()=" + getPartition() + ", getCommand()=" + getCommand() + "]";
	}
	
}