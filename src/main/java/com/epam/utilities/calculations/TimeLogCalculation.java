package com.epam.utilities.calculations;

import com.epam.persistence.entities.TimeLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLogCalculation {

    private static final Logger logger = LogManager.getLogger(TimeLogCalculation.class);

    public static long getTimeLogInterval(Date oldDate, Date newDate) {

        TimeUnit timeUnit = TimeUnit.HOURS;

        Long diffInMillis = 0L;

        logger.trace("getTimeLogInterval: oldDate = " + oldDate.getTime() + " newDate = "+ newDate.getTime());

        diffInMillis = newDate.getTime() - oldDate.getTime();

        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static long getTotalTime(List<TimeLog> timeLogs) {

        Long totalTimeInHours = 0L;

        logger.trace("getTotalTime: timeLogs.size() = " + timeLogs.size());

        for (TimeLog timeLog : timeLogs) {

            totalTimeInHours += getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
        }

        logger.trace("getTotalTime: " + totalTimeInHours);

        return totalTimeInHours;
    }
}
