package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.DataStringBuilder;

public class EnvisalinkNetworkLoginCommand extends WriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "005";

	public EnvisalinkNetworkLoginCommand(String password) {
		super(CODE, new DataStringBuilder().appendStringData(password).build());
	}


	public String getDescription() {
		return "The command is sent by the client after it has created a TCP connection to the TPI to open a session. The TPI will respond with command 505 if the login was successful. The password is the same as the local Envisalink password for the web page.";
	}

}
