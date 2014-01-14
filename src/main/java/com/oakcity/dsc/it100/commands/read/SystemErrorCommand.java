package com.oakcity.dsc.it100.commands.read;

import java.util.HashMap;
import java.util.Map;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class SystemErrorCommand extends ReadCommand implements ICommandHelp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7304181932565391733L;
	public static final String CODE = "502";
	private String errorCode = null;

	private static Map<String, String> errorDescriptionMap = new HashMap<String, String>();

	static {
		errorDescriptionMap.put("017", "Keybus Busy - Installer Mode");
		errorDescriptionMap.put("021", "Requested Partition is out of Range");
		errorDescriptionMap.put("023", "Partition is Not Armed");
		errorDescriptionMap.put("024", "Partition is Not Ready to Arm");
		errorDescriptionMap.put("026",
				"User Code Not Required 028 Virtual Keypad is Disabled");
		errorDescriptionMap.put("029", "Not Valid Parameter");
		errorDescriptionMap
				.put("030", "Keypad Does Not Come Out of Blank Mode");
		errorDescriptionMap.put("031", "IT-100 is already in Thermostat menu");
		errorDescriptionMap.put("032", "IT-100 is Not in Thermostat menu");
		errorDescriptionMap.put("033",
				"No Response from Thermostat (or Escort Module)");
	}

	public String getDescription() {
		return "This command indicates that a Command has been received with a bad checksum. No additional data is available.";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 3) {
			throw new CommandDataParseException(
					"Expected data length of 3 bytes, received "
							+ dataString.length());
		}
		errorCode = new String(dataString);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		String description = errorDescriptionMap.get(errorCode);
		if (description == null) {
			description = "Unknown error. No description available.";
		}
		return description;
	}

	@Override
	public String toString() {
		return "SystemErrorCommand [getErrorCode()=" + getErrorCode()
				+ ", getErrorDescription()=" + getErrorDescription()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
