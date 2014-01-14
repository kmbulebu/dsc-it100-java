package com.oakcity.dsc.it100;

public interface IVirtualKeypadStateChangeEvent extends IStateChangeEvent {
	
	public boolean isVirtualKeypadChanged();
	
	public void updateVirtualKeypad(IVirtualKeypad keypad);

}
