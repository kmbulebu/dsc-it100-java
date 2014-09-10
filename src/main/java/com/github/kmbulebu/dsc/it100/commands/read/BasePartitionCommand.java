package com.github.kmbulebu.dsc.it100.commands.read;




public abstract class BasePartitionCommand extends ReadCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3334123638332691662L;
	private String partitionCode = null;
	private int partition = -1;

	protected BasePartitionCommand() {
		super();
	}

	protected BasePartitionCommand(String rawCommand) throws BadChecksumException,
			CommandLengthException, InvalidCommandException {
		super(rawCommand);
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 1) {
			throw new CommandDataParseException("Expected data length of 1 bytes, received " + dataString.length());
		}
		partitionCode = new String(dataString);
		try {
			partition = Integer.parseInt(partitionCode);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Partition code was not a valid number: " + e.getMessage(), e);
		}
		
		if (partition < 1 || partition > 8) {
			throw new CommandDataParseException("Partition number was out of range [1,8]. Actual value: " + partition);
		}
		
		return;
	}

	public String getPartitionCode() {
		return partitionCode;
	}
	
	public int getPartition() {
		return partition;
	}

	@Override
	public String toString() {
		return "BasePartitionCommand [getPartitionCode()=" + getPartitionCode()
				+ ", getPartition()=" + getPartition() + "]";
	}
	
	

}