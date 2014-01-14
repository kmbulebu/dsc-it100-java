package com.oakcity.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.IZone;
import com.oakcity.dsc.it100.IZoneStateChangeEvent;
import com.oakcity.dsc.it100.mina.handlers.FanOutStateHandler.IStateHandler;

public abstract class ZoneStateHandler implements MessageHandler<IZoneStateChangeEvent>, IStateHandler<IZoneStateChangeEvent> {


	@Override
	public synchronized void handleMessage(IoSession session, IZoneStateChangeEvent event) throws Exception {
		// Retrieve state object, presumably by an ID.
		if (event.isZoneChanged()) {
			final IZone zone = getZone(event.getZone());
			
			event.updateZone(zone);
			
			afterUpdate(zone);
		}
	}
	
	public abstract IZone getZone(int zoneId);
	
	public abstract void afterUpdate(IZone zone);
	
	@Override
	public Class<IZoneStateChangeEvent> getMessageType() {
		return IZoneStateChangeEvent.class;
	}
	

}
