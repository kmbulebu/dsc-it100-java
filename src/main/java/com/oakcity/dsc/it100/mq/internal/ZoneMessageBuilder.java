package com.oakcity.dsc.it100.mq.internal;

import org.json.JSONObject;

public class ZoneMessageBuilder {
	
	JSONObject json = new JSONObject();
	
	public ZoneMessageBuilder withNumber(int number) {
		json.put("number", number);
		return this;
	}
	
	public ZoneMessageBuilder withLabel(String label) {
		json.put("label", label);
		return this;
	}
	
	public ZoneMessageBuilder withOpen(boolean isOpen) {
		json.put("open", isOpen);
		return this;
	}
	
	public ZoneMessageBuilder withFault(boolean isInFault) {
		json.put("inFault", isInFault);
		return this;
	}
	
	public ZoneMessageBuilder withTamper(boolean isInTamper) {
		json.put("inTamper", isInTamper);
		return this;
	}
	
	public ZoneMessageBuilder withAlarm(boolean isInAlarm) {
		json.put("inAlarm", isInAlarm);
		return this;
	}

	public ZoneMessageBuilder withPartition(int number) {
		json.put("partitionInAlarm", number);
		return this;
	}
	
	public String build() {
		return json.toString();
	}
}
