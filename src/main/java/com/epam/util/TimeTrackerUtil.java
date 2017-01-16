package com.epam.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeTrackerUtil {

    private static final Logger logger = LogManager.getLogger(TimeTrackerUtil.class);

    public static long getTimeLogInterval(Date oldDate, Date newDate) {

        TimeUnit timeUnit = TimeUnit.HOURS;

        Long diffInMillis = 0L;

        logger.trace("getTimeLogInterval.oldDate = " + oldDate.getTime() + " newDate = "+ newDate.getTime());

        diffInMillis = newDate.getTime() - oldDate.getTime();

        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }
}
