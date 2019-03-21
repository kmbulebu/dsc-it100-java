package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class EnvisalinkLoginInteractionCommand extends ReadCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8566976140528732230L;

	public static final String CODE = "505";
	
	private int loginCodeValue = -1;
	
	private LoginCode loginCode;

	@Override
	protected void parseData(String dataString)
			throws CommandDataParseException {
		if (dataString.length() != 1) {
			throw new CommandDataParseException("Expected data length of 1 bytes, received " + dataString.length());
		}
		try {
			loginCodeValue = Integer.parseInt(dataString);
		} catch (NumberFormatException e) {
			throw new CommandDataParseException("Envisalink login code was not a valid number: " + e.getMessage(), e);
		}
		
		if (loginCodeValue < 0 || loginCodeValue > 3) {
			throw new CommandDataParseException("Envisalink oogin code was out of range [0,3]. Actual value: " + loginCodeValue);
		}
		loginCode = LoginCode.values()[loginCodeValue];
		return;
	}
	
	public int getLoginCodeValue() {
		return loginCodeValue;
	}
	
	public LoginCode getLoginCode() {
		return loginCode;
	}

	@Override
	public String toString() {
		return "EnvisalinkLoginCommand [loginCodeValue=" + loginCodeValue + ", loginCode=" + loginCode + "]";
	}

	public String getDescription() {
		return "Sent during session login only.\n" + 
				"3 = Request for password, sent after socket setup\n" + 
				"2 = Time out. You did not send a password within 10 seconds. 1 = Password Correct, session established\n" + 
				"0 = Password provided was incorrect";
	}
	
	public enum LoginCode {
		
		FAIL(0),
		SUCCESS(1),
		TIMEOUT(2),
		PASSWORD_REQUESTED(3);
		
		private final int code;
		
		private LoginCode(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
}
