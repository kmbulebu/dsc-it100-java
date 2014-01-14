package com.oakcity.dsc.it100;


public interface ICommandOutput  {

	public Integer getNumber();
	
	public void setNumber(Integer number);

	public String getLabel();

	public void setLabel(String label);

	public Integer getPartition();
	
	public void setPartition(Integer partitionId);
}