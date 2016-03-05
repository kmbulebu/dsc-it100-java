package com.github.kmbulebu.dsc.it100.commands.read;

public abstract class BaseUserCommand extends BasePartitionCommand {

	private int userCode;

	@Override
	protected void parseData(String dataString) throws CommandDataParseException {
		if (dataString.length() != 5) {
			throw new CommandDataParseException("Expected data length of 5 bytes, received " + dataString.length());
		}

		String userCodeString = dataString.substring(1, 5);
		try {
			userCode = Integer.parseInt(userCodeString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Label code was not a valid number: " + e.getMessage(), e);
		}

		if (userCode < 1 || userCode > 42) {
			throw new CommandDataParseException("User code was not in range [1,42]. Received " + userCode + " instead.");
		}

		super.parseData(dataString.substring(0, 1));
	}

	public int getUserCode() {
		return userCode;
	}
}
