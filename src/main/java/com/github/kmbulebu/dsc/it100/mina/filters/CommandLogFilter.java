package com.github.kmbulebu.dsc.it100.mina.filters;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.IMarkerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.BasicMarkerFactory;

public class CommandLogFilter extends IoFilterAdapter {

    private final IMarkerFactory markerFactory = new BasicMarkerFactory();
    private final Logger logger;
    private final Level level;

    public CommandLogFilter(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        if (message instanceof ReadCommand) {
            log(level, markerFactory.getMarker("RECEIVED"), message.toString());
        }
        super.messageReceived(nextFilter, session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        if (writeRequest.getMessage() instanceof WriteCommand) {
            log(level, markerFactory.getMarker("SENT"), writeRequest.getMessage().toString());
        }
        super.messageSent(nextFilter, session, writeRequest);
    }

    private void log(Level level, Marker marker, String message) {
        switch (level) {
            case TRACE:
                logger.trace(marker, message);
                break;
            case DEBUG:
                logger.debug(marker, message);
                break;
            case INFO:
                logger.info(marker, message);
                break;
            case WARN:
                logger.warn(marker, message);
                break;
            case ERROR:
                logger.error(marker, message);
                break;
        }
    }

}
