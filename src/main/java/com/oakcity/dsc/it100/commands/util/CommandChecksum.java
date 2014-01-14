package com.oakcity.dsc.it100.commands.util;

public class CommandChecksum {

	public static boolean verifyChecksum(String command, String data, String sentChecksum) {
		return calculateChecksum(command, data).equals(sentChecksum);
	}

	public static String calculateChecksum(String command, String data) {
		int checksumInt = 0;
		
		// Add command byte ascii values.
		for (int i = 0; i < command.length(); i++) {
			checksumInt = checksumInt + command.codePointAt(i);
		}
		
		// Add data byte ascii values
		for (int i = 0; i < data.length(); i++) {
			checksumInt = checksumInt + data.codePointAt(i);
		}
		
		// Truncate to 8 bits.
		//byte checksumByte = (byte) (0x0F & checksumInt); 
		String checksumHexString = Integer.toHexString(checksumInt).toUpperCase();
		
		StringBuilder builder = new StringBuilder();
		if (checksumHexString.length() >= 2) {
			builder.append(checksumHexString, checksumHexString.length() - 2, checksumHexString.length());
		} else if (checksumHexString.length() == 1) {
			builder.append('0');
			builder.append(checksumHexString);
		} else {
			builder.append("00");
		}
		return builder.toString();
	}

}
