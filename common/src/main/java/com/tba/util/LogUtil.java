package com.tba.util;

import org.apache.log4j.Logger;

public class LogUtil {
    private LogUtil(){};

    public static void setDebugLog(Logger logger, String log)
    {
        if(logger.isDebugEnabled())
            logger.debug(log);
    }

    public static void setInfoLog(Logger logger, String log)
    {
        if(logger.isInfoEnabled())
            logger.info(log);
    }

    public static void setTraceLog(Logger logger, String log)
    {
        if(logger.isTraceEnabled())
            logger.trace(log);
    }
    public static void setErrorLog(Logger logger, String log)
    {
        logger.error(log);
    }
}
