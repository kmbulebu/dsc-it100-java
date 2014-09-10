package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;
import com.github.kmbulebu.dsc.it100.commands.util.DataStringBuilder;

public class VirtualKeypadControlCommand extends WriteCommand implements ICommandHelp {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 610371346540454935L;

	public static final String CODE = "058";
	
	private final boolean isEnabled;

	public VirtualKeypadControlCommand(boolean isEnabled) {
		super(CODE, new DataStringBuilder().appendBoolean(isEnabled).build());
		this.isEnabled = isEnabled;
	}


	public String getDescription() {
		return "This command enables/disables the virtual keypad. When enabled, all virtual keypad commands (Virt) from the application will be processed. All virtual keypad responses (i.e., menu, status lights updates) are automatically initiated by the IT-100 and sent to the application. When this com- mand is disabled all virtual keypad commands (Virt) are ignored. The IT-100 does not send a response to this command unless there is a system error.";
	}


	public boolean isEnabled() {
		return isEnabled;
	}
	
	


}
