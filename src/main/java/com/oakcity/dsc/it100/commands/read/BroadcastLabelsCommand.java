package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.commands.ICommandHelp;

public class BroadcastLabelsCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9032117297479913873L;
	
	public static final String CODE = "570";
	
	private String labelCodeString;
	private int labelCode;
	private String label;
	private LabelType type;
	private int partition;
	private int zone;
	private int commandOutput;
	
	public enum LabelType {
		
		ZONE("Zone Label"),
		FIRE_ALARM("Fire Alarm Label"),
		FAILED_TO_ARM("Failed to Arm Label"),
		ALARM_WHEN_ARMED("Alarm when Armed Label"),
		PARTITION("Partition Label"),
		COMMAND_OUTPUT("Command Output Label");
		
		private final String description;
		
		LabelType(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

	}

	public String getDescription() {
		return "The IT-100 sends this command in response to the following application command: Label Request (002)";
	}

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 35) {
			throw new CommandDataParseException("Expected data length of 35 bytes, received " + dataString.length());
		}
		labelCodeString = dataString.substring(0, 3);
		try {
			labelCode = Integer.parseInt(labelCodeString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Label code was not a valid number: " + e.getMessage(), e);
		}
		
		if (labelCode < 1 || labelCode > 151) {
			throw new CommandDataParseException("Label code was not in range [1,151]. Received " + labelCode + " instead.");
		}
		
		if (labelCode > 0 && labelCode < 65) {
			type = LabelType.ZONE;
			zone = labelCode;
		} else if (labelCode == 65) {
			type = LabelType.FIRE_ALARM;
		} else if (labelCode == 66) {
			type = LabelType.FAILED_TO_ARM;
		} else if (labelCode == 67) {
			type = LabelType.ALARM_WHEN_ARMED;
		} else if (labelCode > 100 && labelCode < 109) {
			type = LabelType.PARTITION;
			partition = labelCode - 100;
		} else if (labelCode > 119 && labelCode < 152) {
			type = LabelType.COMMAND_OUTPUT;
			commandOutput = ((labelCode - 120) % 4) + 1;
			partition = (labelCode - 120) / 4;
		} else {
			type = null;
		}
		 
		label = dataString.substring(3).trim();
		
	}
	
	public int getPartition() {
		return partition;
	}

	public int getZone() {
		return zone;
	}

	public int getCommandOutput() {
		return commandOutput;
	}

	public LabelType getType() {
		return type;
	}

	public String getLabelCodeString() {
		return labelCodeString;
	}
	
	public int getLabelCode() {
		return labelCode;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return "BroadcastLabelsCommand [labelCodeString=" + labelCodeString + ", labelCode=" + labelCode + ", label="
				+ label + ", type=" + type + ", partition=" + partition + ", zone=" + zone + ", commandOutput="
				+ commandOutput + "]";
	}

	

	

	
	
	

}
