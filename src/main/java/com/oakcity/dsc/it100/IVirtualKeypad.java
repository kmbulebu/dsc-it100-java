package com.oakcity.dsc.it100;

import com.oakcity.dsc.it100.commands.read.LEDStatusCommand.LED;
import com.oakcity.dsc.it100.commands.read.LEDStatusCommand.LEDStatus;

public interface IVirtualKeypad {

	public String getLcdLine1();
	
	public String getLcdLine2();
	
	public void setLcdLine1(String line1);
	
	public void setLcdLine2(String line2);
	
	public int getLcdCursorLineNumber();
	
	public void setLcdCursorLineNumber(int lineNumber);
	
	public int getLcdCursorColumnNumber();
	
	public void setLcdCursorColumnNumber(int columnNumber);
	
	public LEDStatus getLedStatus(LED led);
	
	public void setLedStatus(LED led, LEDStatus status);
	
	public LEDStatus getReadyLedStatus();
	
	public LEDStatus getArmedLedStatus();
	
	public LEDStatus getMemoryLedStatus();
	
	public LEDStatus getBypassLedStatus();
	
	public LEDStatus getTroubleLedStatus();
	
	public LEDStatus getProgramLedStatus();
	
	public LEDStatus getFireLedStatus();

	public LEDStatus getBacklightLedStatus();
	
	public LEDStatus getAcLedStatus();

}
