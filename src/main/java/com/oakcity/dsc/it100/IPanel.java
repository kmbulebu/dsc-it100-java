package com.oakcity.dsc.it100;

public interface IPanel {

	public boolean isInKeybusFault();

	public void setInKeybusFault(boolean isInKeybusFault);

	public boolean isInFireTrouble();

	public void setInFireTrouble(boolean isInFireTrouble);

	public boolean isPanelBatteryTrouble();

	public void setPanelBatteryTrouble(boolean panelBatteryTrouble);

	public boolean isPanelACTrouble();

	public void setPanelACTrouble(boolean panelACTrouble);

	public boolean isSystemBellTrouble();

	public void setSystemBellTrouble(boolean systemBellTrouble);

	public boolean isInGeneralSystemTamper();

	public void setInGeneralSystemTamper(boolean isInGeneralSystemTamper);

	public int getBaudRate();

	public void setBaudRate(int baudRate);

}