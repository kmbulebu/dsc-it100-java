package com.oakcity.dsc.it100.mina.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import com.oakcity.dsc.it100.IPartition;
import com.oakcity.dsc.it100.IPartitionStateChangeEvent;

public abstract class PartitionStateHandler implements MessageHandler<IPartitionStateChangeEvent> {


	@Override
	public synchronized void handleMessage(IoSession session, IPartitionStateChangeEvent event) throws Exception {
		// Retrieve state object, presumably by an ID.
		if (event.isPartitionChanged()) {
			final IPartition zone = getPartition(event.getPartition());
			
			event.updatePartition(zone);
			
			afterUpdate(zone);
		}
	}
	
	public abstract IPartition getPartition(int partitionId);
	
	public abstract void afterUpdate(IPartition partition);
	

}
