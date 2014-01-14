package com.oakcity.dsc.it100;

public interface IPanelStateChangeEvent extends IStateChangeEvent{
	
	public boolean isSystemChanged();
	
	public void updateSystem(IPanel system);

}
