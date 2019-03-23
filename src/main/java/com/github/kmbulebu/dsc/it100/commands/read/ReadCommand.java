package com.github.kmbulebu.dsc.it100.commands.read;

import java.io.Serializable;

import com.github.kmbulebu.dsc.it100.commands.ICommand;
import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.CommandChecksum;


public abstract class ReadCommand implements ICommand, ICommandHelp, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2680305130025520079L;
	private String command;
	private String data;
	
	public static final int MINIMUM_LENGTH = 5;
	
	protected ReadCommand() {
		super();
	}
	
	protected ReadCommand(String rawCommand) throws BadChecksumException, CommandLengthException, InvalidCommandException {
		init(rawCommand);
	}
	
	protected void init(String rawCommand) throws BadChecksumException, CommandLengthException, InvalidCommandException {
		String commandString;
		if (rawCommand.endsWith("\r\n")) {
			commandString = rawCommand.substring(0, rawCommand.length() - 2);
		} else {
			commandString = rawCommand;
		}
		final int stringLength = commandString.length();

		if (stringLength < 5) {
			throw new CommandLengthException(stringLength);
		}
		
		/*if (commandString.charAt(stringLength-2) != '\r' || commandString.charAt(stringLength-1) != '\n') {
			throw new InvalidCommandException("Commands must be terminated with CRLF ASCII characters.");
		}*/
		
		String command = commandString.substring(0, 3);
		String data = commandString.substring(3, stringLength - 2);
		String checksum = commandString.substring(stringLength - 2);
		
		if (!CommandChecksum.verifyChecksum(command, data, checksum)) {
			throw new BadChecksumException();
		}

		this.command = command;
		this.data = data;
		
		parseData(data);
	}
	
	/* (non-Javadoc)
	 * @see com.oakcity.dsc.it100.commands.ICommand#getCommandCode()
	 */
	public String getCommandCode() {
		return command;
	}

	/* (non-Javadoc)
	 * @see com.oakcity.dsc.it100.commands.ICommand#getData()
	 */
	public String getData() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReadCommand other = (ReadCommand) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	protected abstract void parseData(String dataString) throws CommandDataParseException;

	@Override
	public String toString() {
		return "Command [command=" + command + ", data=" + data + "]";
	}	
	
	public static class InvalidCommandException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7755049003780875350L;

		public InvalidCommandException() {
			super();
		}

		public InvalidCommandException(String message, Throwable cause,
				boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public InvalidCommandException(String message, Throwable cause) {
			super(message, cause);
		}

		public InvalidCommandException(String message) {
			super(message);
		}

		public InvalidCommandException(Throwable cause) {
			super(cause);
		}
	
	}
	
	public static class BadChecksumException extends InvalidCommandException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1453003256552860476L;

		public BadChecksumException() {
			super("Command failed checksum verification.");
		}
	
	}
	
	public static class CommandDataParseException extends InvalidCommandException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -872633216322069153L;

		public CommandDataParseException() {
			super();
		}

		public CommandDataParseException(String message, Throwable cause,
				boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public CommandDataParseException(String message, Throwable cause) {
			super(message, cause);
		}

		public CommandDataParseException(String message) {
			super(message);
		}

		public CommandDataParseException(Throwable cause) {
			super(cause);
		}
		
		
	}
	
	public static class UnknownCommandException extends InvalidCommandException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7815547466403107095L;

		public UnknownCommandException(String commandString) {
			super("Command was not recognized. Received " + commandString);
		}
		
		
	}
	
	public static class CommandLengthException extends InvalidCommandException {

	

		/**
		 * 
		 */
		private static final long serialVersionUID = -209181668308808610L;

		public CommandLengthException(int length) {
			super("Received " + length + " bytes, expected at least 5");
		}
		
		
	}

	
}
