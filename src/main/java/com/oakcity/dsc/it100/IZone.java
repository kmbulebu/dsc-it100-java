package com.oakcity.dsc.it100;

import java.util.Set;

public interface IZone  {

	public int getNumber();

	public String getLabel();

	public void setLabel(String label);

	public boolean isOpen();

	public void setOpen(boolean isOpen);

	public boolean isInTamper();

	public void setInTamper(boolean isInTamper);

	public boolean isInFault();

	public void setInFault(boolean isInFault);

	public boolean getIsInAlarm(int partition);

	public Set<Integer> getPartitionsInAlarm();

	public void setIsInAlarm(boolean isInAlarm, int partition);

}