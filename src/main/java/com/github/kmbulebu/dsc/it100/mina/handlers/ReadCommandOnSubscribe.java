package com.github.kmbulebu.dsc.it100.mina.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.ExceptionHandler;
import org.apache.mina.handler.demux.MessageHandler;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;

import rx.Subscriber;

public class ReadCommandOnSubscribe implements rx.Observable.OnSubscribe<ReadCommand>, MessageHandler<ReadCommand>, ExceptionHandler<Exception>  {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final List<Subscriber<? super ReadCommand>> subscribers;
	
	public ReadCommandOnSubscribe() {
		this.subscribers = Collections.synchronizedList(new ArrayList<Subscriber<? super ReadCommand>>());
	}

	@Override
	public void call(final Subscriber<? super ReadCommand> subscriber) {
		this.subscribers.add(subscriber);
	}


	@Override
	public void handleMessage(IoSession session, ReadCommand message) throws Exception {
		for (Subscriber<? super ReadCommand> subscriber: subscribers) {
			subscriber.onNext(message);
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Exception cause) throws Exception {
		LOGGER.error("Exception caught while communicating with the IT-100: " + cause.getMessage(), cause);
		// TODO Determine how we want to handle errors. One error and the Observable stops receiving events.
		if (cause instanceof IOException) {
			// We are probably doomed and should notify our consumers.
			for (Subscriber<? super ReadCommand> subscriber: subscribers) {
				subscriber.onError(cause);
			}		
		}
	}

}
