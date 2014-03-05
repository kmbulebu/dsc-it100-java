package com.oakcity.dsc.it100.mq.internal;

import java.util.Collection;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.oakcity.dsc.it100.mq.ZoneOpenListener;

public class ZoneMessageListener implements MessageListener {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final Collection<ZoneOpenListener> zoneOpenListeners;
	
	public ZoneMessageListener(Collection<ZoneOpenListener> zoneOpenListeners) {
		this.zoneOpenListeners = zoneOpenListeners;
	}

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		JSONObject json;
		try {
			json = new JSONObject(tm.getText());
		} catch (JSONException e) {
			LOGGER.error("Incorrectly formatted zone MQ message:" + e.getMessage(), e);
			return;
		} catch (JMSException e) {
			LOGGER.fatal("Could not read zone MQ message:" + e.getMessage(), e);
			return;
		}
		if (json.has("number") && json.has("open")) {
			for (ZoneOpenListener listener : zoneOpenListeners) {
				listener.onZoneOpen(json.getInt("number"), json.optString("label"), json.getBoolean("open"));
			}
		}
	}

}
