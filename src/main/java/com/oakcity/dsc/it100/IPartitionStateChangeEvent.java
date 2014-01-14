package com.oakcity.dsc.it100;

public interface IPartitionStateChangeEvent extends IStateChangeEvent {
	
	public boolean isPartitionChanged();
	
	public int getPartition();
	
	public void updatePartition(IPartition partition);

}
