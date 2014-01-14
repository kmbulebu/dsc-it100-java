package com.oakcity.dsc.it100.commands.read;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;
import com.oakcity.dsc.it100.commands.ICommandHelp;

public class PartitionArmedCommand extends BasePartitionCommand implements
		ICommandHelp, IPartitionStateChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8821613516738718206L;
	public static final String CODE = "652";
	private ArmedMode mode;

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 2) {
			throw new CommandDataParseException(
					"Expected data length of 2 bytes, received "
							+ dataString.length());
		}

		String modeCode = dataString.substring(1);
		int modeInt;
		try {
			modeInt = Integer.parseInt(modeCode);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException(
					"Mode code was not a valid number: " + e.getMessage(), e);
		}

		switch (modeInt) {
		case 0:
			mode = ArmedMode.AWAY;
			break;
		case 1:
			mode = ArmedMode.STAY;
			break;
		case 2:
			mode = ArmedMode.AWAY_NO_DELAY;
			break;
		case 3:
			mode = ArmedMode.STAY_NO_DELAY;
			break;
		default:
			throw new CommandDataParseException(
					"Mode was not in the valid range [0,3]. Actual value: "
							+ modeInt);
		}
		super.parseData(dataString.substring(0, 1));
	}

	public enum ArmedMode {

		AWAY("Away"), STAY("Stay"), AWAY_NO_DELAY("Away, No Delay"), STAY_NO_DELAY(
				"Stay, No Delay");

		private final String description;

		ArmedMode(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

	}

	public String getDescription() {
		return "This command indicates that a partition has been armed and the mode it has been armed in. This command is sent at the end of an Exit Delay and after an alarm if the Bell Cutoff expires.";
	}

	@Override
	public String toString() {
		return "PartitionArmedCommand [getMode()=" + getMode()
				+ ", toString()=" + super.toString() + "]";
	}

	public ArmedMode getMode() {
		return mode;
	}

	@Override
	public boolean isPartitionChanged() {
		return true;
	}

	@Override
	public void updatePartition(IPartition partition) {
		partition.setInExitDelay(false);
		partition.setArmed(true);
		partition.setArmedMode(getMode());
		partition.setReady(false);
		partition.setReadyToForceArm(false);
	}

}
