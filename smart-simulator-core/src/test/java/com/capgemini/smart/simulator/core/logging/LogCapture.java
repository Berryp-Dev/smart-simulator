package com.capgemini.smart.simulator.core.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

import java.util.List;

/** Appender di test che raccoglie gli eventi di log (con livello, messaggio e MDC) di una classe. */
public class LogCapture implements AutoCloseable {

    private final Logger logger;
    private final ListAppender<ILoggingEvent> appender = new ListAppender<>();

    public LogCapture(Class<?> loggedClass) {
        this.logger = (Logger) LoggerFactory.getLogger(loggedClass);
        this.logger.setLevel(Level.INFO);
        this.appender.start();
        this.logger.addAppender(appender);
    }

    public List<ILoggingEvent> getEvents() {
        return appender.list;
    }

    @Override
    public void close() {
        logger.detachAppender(appender);
    }
}
