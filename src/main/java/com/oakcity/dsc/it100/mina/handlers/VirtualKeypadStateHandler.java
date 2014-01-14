package com.oakcity.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.IVirtualKeypad;
import com.oakcity.dsc.it100.IVirtualKeypadStateChangeEvent;
import com.oakcity.dsc.it100.mina.handlers.FanOutStateHandler.IStateHandler;

public abstract class VirtualKeypadStateHandler implements MessageHandler<IVirtualKeypadStateChangeEvent>, IStateHandler<IVirtualKeypadStateChangeEvent> {


	@Override
	public synchronized void handleMessage(IoSession session, IVirtualKeypadStateChangeEvent event) throws Exception {
		// Retrieve state object, presumably by an ID.
		if (event.isVirtualKeypadChanged()) {
			final IVirtualKeypad keypad = getVirtualKeypad();
			
			event.updateVirtualKeypad(keypad);
			
			afterUpdate(keypad);
		}
	}
	
	public abstract IVirtualKeypad getVirtualKeypad();
	
	public abstract void afterUpdate(IVirtualKeypad system);
	
	@Override
	public Class<IVirtualKeypadStateChangeEvent> getMessageType() {
		return IVirtualKeypadStateChangeEvent.class;
	}
	

}
