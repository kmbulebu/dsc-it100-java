package com.oakcity.dsc.it100;

import com.oakcity.dsc.it100.commands.read.PartitionArmedCommand.ArmedMode;

public interface IPartition  {

	public int getNumber();

	public String getLabel();

	public void setLabel(String label);

	public boolean isInAlarm();

	public void setInAlarm(boolean isInAlarm);

	public boolean isReady();

	public void setReady(boolean isReady);

	public boolean isReadyToForceArm();

	public void setReadyToForceArm(boolean isReadyToForceArm);

	public boolean isBusy();

	public void setBusy(boolean isBusy);

	public boolean isArmed();

	public void setArmed(boolean isArmed);

	public ArmedMode getArmedMode();

	public void setArmedMode(ArmedMode armedMode);

	public boolean isInEntryDelay();

	public void setInEntryDelay(boolean isInEntryDelay);

	public boolean isInExitDelay();

	public void setInExitDelay(boolean isInExitDelay);

	public boolean isInTrouble();

	public void setInTrouble(boolean isInTrouble);
	
	public boolean isInCommandOutput();
	
	public void setIsInCommandOutput(boolean isInCommandOutput);

}