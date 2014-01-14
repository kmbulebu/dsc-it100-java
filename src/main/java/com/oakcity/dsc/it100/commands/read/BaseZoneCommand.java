package com.oakcity.dsc.it100.commands.read;




public abstract class BaseZoneCommand extends ReadCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5889588266251076429L;
	private String zoneCode = null;
	private int zone = -1;

	protected BaseZoneCommand() {
		super();
	}

	protected BaseZoneCommand(String rawCommand) throws BadChecksumException,
			CommandLengthException, InvalidCommandException {
		super(rawCommand);
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 3) {
			throw new CommandDataParseException("Expected data length of 3 bytes, received " + dataString.length());
		}
		zoneCode = new String(dataString);
		try {
			zone = Integer.parseInt(zoneCode);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Zone code was not a valid number: " + e.getMessage(), e);
		}
		
		if (zone < 1 || zone > 64) {
			throw new CommandDataParseException("Zone number was not in the valid range [1,64]. Actual value: " + zone);
		}
		return;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	@Override
	public String toString() {
		return "BaseZoneCommand [getZoneCode()=" + getZoneCode()
				+ ", getZone()=" + getZone() + ", toString()="
				+ super.toString() + "]";
	}

	public int getZone() {
		return zone;
	}
	
	

}