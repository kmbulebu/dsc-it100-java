package com.github.kmbulebu.dsc.it100.commands.read;




public abstract class BasePartitionZoneCommand extends BaseZoneCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2624449552879411667L;
	
	private String partitionCode = null;
	private int partition = -1;
	
	protected String getPartitionCode() {
		return partitionCode;
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 4) {
			throw new CommandDataParseException("Expected data length of 4 bytes, received " + dataString.length());
		}
		partitionCode = dataString.substring(0, 1);
		
		try {
			partition = Integer.parseInt(partitionCode);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Partition code was not a valid number: " + e.getMessage(), e);
		}
		
		if (partition < 1 || partition > 8) {
			throw new CommandDataParseException("Partition number was out of range [1,8]. Actual value: " + partition);
		}
		
		// BaseZoneCommand expects dataString to be exactly 3 bytes. Cut out the partition before passing.
		super.parseData(dataString.substring(1));
	}

	public int getPartition() {
		return partition;
	}

	@Override
	public String toString() {
		return "BasePartitionZoneCommand [getPartitionCode()="
				+ getPartitionCode() + ", getPartition()=" + getPartition()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}
