package com.oakcity.dsc.it100;

public interface ICommandOutputStateChangeEvent extends IStateChangeEvent{
	
	public boolean isCommandOutputChanged();
	
	public int getCommandOutput();
	
	public int getPartition();
	
	public void updateCommandOutput(ICommandOutput output);

}
