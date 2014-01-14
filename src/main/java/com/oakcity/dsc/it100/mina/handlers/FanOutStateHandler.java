package com.oakcity.dsc.it100.mina.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

@SuppressWarnings("rawtypes")
public class FanOutStateHandler implements MessageHandler {
	
	private final Map<Class, MessageHandler> handlerMap = new HashMap<Class, MessageHandler>();

	public void addMessageHandler(Class supportedType, MessageHandler handler) {
		handlerMap.put(supportedType, handler);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(IoSession session, Object message) throws Exception {
		for (Class<Object> handlerType : handlerMap.keySet()) {
			if (handlerType.isInstance(message)) {
				handlerMap.get(handlerType).handleMessage(session, message);
			}
		}
	}
	
	public static interface IStateHandler<T> {
		
		public Class<T> getMessageType();
	}

}
