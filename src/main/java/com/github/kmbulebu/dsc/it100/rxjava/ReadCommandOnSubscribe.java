package com.github.kmbulebu.dsc.it100.rxjava;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.apache.mina.handler.demux.ExceptionHandler;
import org.apache.mina.handler.demux.MessageHandler;

import rx.Subscriber;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;

public class ReadCommandOnSubscribe implements rx.Observable.OnSubscribe<ReadCommand> {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private final DemuxingIoHandler demuxIoHandler;
	
	public ReadCommandOnSubscribe(DemuxingIoHandler demuxIoHandler) {
		this.demuxIoHandler = demuxIoHandler;
	}

	@Override
	public void call(final Subscriber<? super ReadCommand> subscriber) {
		final MessageHandler<ReadCommand> messageHandler = new MessageHandler<ReadCommand>() {

			@Override
			public void handleMessage(IoSession session, ReadCommand message) throws Exception {
				subscriber.onNext(message);
			}
			
		};
		
		final ExceptionHandler<Throwable> exceptionHandler = new ExceptionHandler<Throwable>() {

			@Override
			public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
				LOGGER.error("Exception caught while communicating with the IT-100: " + cause.getMessage(), cause);
				// TODO Determine how we want to handle errors. One error and the Observable stops receiving events.
				if (cause instanceof IOException) {
					// We are probably doomed and should notify our consumers.
					subscriber.onError(cause);
				}
			}
			
		};
		
		demuxIoHandler.addExceptionHandler(Throwable.class, exceptionHandler);
		demuxIoHandler.addReceivedMessageHandler(ReadCommand.class, messageHandler);
	}

}
