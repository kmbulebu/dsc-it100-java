package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class SoftwareVersionCommand extends ReadCommand implements ICommandHelp {

	public static final String CODE = "908";

	private String softwareVersion;

	private String subVersion;

	private String futureUse;

	@Override
	public String getDescription() {
		return "The It-100 sends this command following power up and when the following command is sent by the application (Status Command)";
	}

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 6) {
			throw new CommandDataParseException("Expected data length of 6 bytes, received " + dataString.length());
		}

		softwareVersion = dataString.substring(0, 2);
		subVersion = dataString.substring(2, 4);
		futureUse = dataString.substring(4, 6);
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public String getSubVersion() {
		return subVersion;
	}

	public String getFutureUse() {
		return futureUse;
	}

	@Override
	public String toString() {
		return "SoftwareVersionCommand [toString()=" + super.toString() + "]";
	}
}
