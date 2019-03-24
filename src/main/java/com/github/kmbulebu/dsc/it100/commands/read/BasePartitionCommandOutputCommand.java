package com.github.kmbulebu.dsc.it100.commands.read;




public abstract class BasePartitionCommandOutputCommand extends BasePartitionCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2624449552879411667L;

	private int command = -1;
	

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException("Expected data length of 2 bytes, received " + dataString.length());
		}
		final String commandString = dataString.substring(1, 2);
		
		try {
			command = Integer.parseInt(commandString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Command code was not a valid number: " + e.getMessage(), e);
		}
		
		if (command < 1 || command > 4) {
			throw new CommandDataParseException("Command number was out of range [1,4]. Actual value: " + command);
		}
		
		// BasePartitionCommand expects dataString to be exactly 1 byte. Cut out the command before passing.
		super.parseData(dataString.substring(0, 1));
	}

	public int getCommand() {
		return command;
	}

	@Override
	public String toString() {
		return "BasePartitionCommandOutputCommand [getData()=" + getData() + ", getPartition()=" + getPartition()
				+ ", getCommand()=" + getCommand() + "]";
	}
	
	

}
