package com.oakcity.dsc.it100.mina.handlers.rx;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.apache.mina.handler.demux.MessageHandler;

import rx.Subscriber;

import com.oakcity.dsc.it100.commands.read.ReadCommand;

public class ReadCommandOnSubscribe implements rx.Observable.OnSubscribe<ReadCommand> {

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
		/*final ExceptionHandler<Throwable> exceptionHandler = new ExceptionHandler<Throwable>() {

			@Override
			public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
				subscriber.onError(cause);
			}
			
		};
		
		demuxIoHandler.addExceptionHandler(Throwable.class, exceptionHandler);*/
		demuxIoHandler.addReceivedMessageHandler(ReadCommand.class, messageHandler);
	}

}
