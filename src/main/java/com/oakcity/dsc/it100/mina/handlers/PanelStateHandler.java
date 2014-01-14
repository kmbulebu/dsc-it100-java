package com.oakcity.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.IPanel;
import com.oakcity.dsc.it100.IPanelStateChangeEvent;
import com.oakcity.dsc.it100.mina.handlers.FanOutStateHandler.IStateHandler;

public abstract class PanelStateHandler implements MessageHandler<IPanelStateChangeEvent>, IStateHandler<IPanelStateChangeEvent> {


	@Override
	public synchronized void handleMessage(IoSession session, IPanelStateChangeEvent event) throws Exception {
		// Retrieve state object, presumably by an ID.
		if (event.isSystemChanged()) {
			final IPanel system = getPanel();
			
			event.updateSystem(system);
			
			afterUpdate(system);
		}
	}
	
	public abstract IPanel getPanel();
	
	public abstract void afterUpdate(IPanel system);
	
	@Override
	public Class<IPanelStateChangeEvent> getMessageType() {
		return IPanelStateChangeEvent.class;
	}
	

}
