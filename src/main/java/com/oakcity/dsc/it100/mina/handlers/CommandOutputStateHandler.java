package com.oakcity.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.ICommandOutput;
import com.oakcity.dsc.it100.ICommandOutputStateChangeEvent;
import com.oakcity.dsc.it100.mina.handlers.FanOutStateHandler.IStateHandler;

public abstract class CommandOutputStateHandler implements MessageHandler<ICommandOutputStateChangeEvent>, IStateHandler<ICommandOutputStateChangeEvent> {


	@Override
	public synchronized void handleMessage(IoSession session, ICommandOutputStateChangeEvent event) throws Exception {
		// Retrieve state object, presumably by an ID.
		if (event.isCommandOutputChanged()) {
			final ICommandOutput output = getCommandOutput(event.getCommandOutput(), event.getPartition());
			
			event.updateCommandOutput(output);
			
			afterUpdate(output);
		}
	}
	
	public abstract ICommandOutput getCommandOutput(int output, int partition);
	
	public abstract void afterUpdate(ICommandOutput commandOutput);
	
	@Override
	public Class<ICommandOutputStateChangeEvent> getMessageType() {
		return ICommandOutputStateChangeEvent.class;
	}
	

}
