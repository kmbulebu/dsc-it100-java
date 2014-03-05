package com.oakcity.dsc.it100.mq.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.mina.handlers.ZoneStateHandler;

public class MqZoneStateHandler extends ZoneStateHandler {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final Map<Integer, IZone> zones = new HashMap<Integer, IZone>(128);
	
	private final Session mqSession;
	private final Topic mqTopic;
	private final MessageProducer mqProducer;
	
	public MqZoneStateHandler(Session mqSession) throws JMSException {
		this.mqSession = mqSession;
		this.mqTopic = mqSession.createTopic("zones");
        this.mqProducer = mqSession.createProducer(mqTopic);
	}

	@Override
	public synchronized IZone getZone(int zoneId) {
		IZone zone = zones.get(zoneId);
		if (zone == null) {
			zone = new Zone(zoneId);
			zones.put(zoneId, zone);
		}
		return zone;
	}

	@Override
	public void afterUpdate(IZone zone) {
		Zone theZone = (Zone) zone;
		if (theZone.messageToPublish != null) {
			try {
				mqProducer.send(mqSession.createTextMessage(theZone.getMessageToPublish()));
			} catch (JMSException e) {
				LOGGER.fatal("Could not send zone MQ message: " + e.getMessage(), e);
			} finally {
				theZone.messageToPublish = null;
			}
		}
	}
	
	private static class Zone implements IZone {
		
		private int number;
		private String label;
		private boolean open;
		private boolean inTamper;
		private boolean inFault;
		private Set<Integer> partitionsInAlarm = new HashSet<Integer>();
		
		private String messageToPublish = null;
		
		
		public Zone(int zoneId) {
			this.number = zoneId;
		}
		
		public String getMessageToPublish() {
			return messageToPublish;
		}

		@Override
		public int getNumber() {
			return number;
		}

		@Override
		public String getLabel() {
			return label;
		}
		
		@Override
		public void setLabel(String label) {
			messageToPublish = new ZoneMessageBuilder().withNumber(number).withLabel(label).build();
			this.label = label;
		}

		@Override
		public boolean isOpen() {
			return open;
		}
		
		@Override
		public void setOpen(boolean open) {
			messageToPublish = new ZoneMessageBuilder().withNumber(number).withOpen(open).build();
			this.open = open;
		}
		
		@Override
		public boolean isInTamper() {
			return inTamper;
		}

		@Override
		public void setInTamper(boolean inTamper) {
			messageToPublish = new ZoneMessageBuilder().withNumber(number).withTamper(inTamper).build();
			this.inTamper = inTamper;
		}

		@Override
		public boolean isInFault() {
			return inFault;
		}

		@Override
		public void setInFault(boolean inFault) {
			messageToPublish = new ZoneMessageBuilder().withNumber(number).withFault(inFault).build();
			this.inFault = inFault;
		}


		@Override
		public Set<Integer> getPartitionsInAlarm() {
			return partitionsInAlarm;
		}

		@Override
		public void setIsInAlarm(boolean isInAlarm, int partition) {
			messageToPublish = new ZoneMessageBuilder().withNumber(number).withAlarm(isInAlarm).withPartition(partition).build();
			if (isInAlarm) {
				partitionsInAlarm.add(partition);
			} else {
				partitionsInAlarm.remove(partition);
			}
		}

		@Override
		public boolean getIsInAlarm(int partition) {
			return partitionsInAlarm.contains(partition);
		}

		@Override
		public String toString() {
			return "Zone [number=" + number + ", label=" + label + ", open=" + open + ", inTamper=" + inTamper
					+ ", inFault=" + inFault + ", partitionsInAlarm=" + partitionsInAlarm + ", messageToPublish="
					+ messageToPublish + "]";
		}
		
		
		
	}

}
