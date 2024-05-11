package managers;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger extends AbstractMatcherFilter<ILoggingEvent> {

    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(CustomLogger.class);
    }

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        LoggingEvent loggingEvent = (LoggingEvent) iLoggingEvent;
        if (loggingEvent.getLoggerName().contains("CustomLogger")){
            return  FilterReply.NEUTRAL;
        } else {
            return FilterReply.DENY;
        }
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debugFormat(String message, Object... objects) {
        logger.debug(String.format(message, objects));
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void infoFormat(String message, Object... objects) {
        logger.info(String.format(message, objects));
    }

    public static void warn(String message, Throwable e) {
        logger.warn(message, e);
    }

    public static void warnFormat(String message, Object... objects) {
        logger.warn(String.format(message, objects));
    }

}
