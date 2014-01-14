package com.oakcity.dsc.it100;

public interface IZoneStateChangeEvent extends IStateChangeEvent{
	
	public boolean isZoneChanged();
	
	public int getZone();
	
	public void updateZone(IZone zone);

}
