package com.epam.util;

import com.epam.model.TimeLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeTrackerUtil {

    private static final Logger logger = LogManager.getLogger(TimeTrackerUtil.class);

    public static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public static long getTimeLogInterval(Date oldDate, Date newDate) {

        TimeUnit timeUnit = TimeUnit.HOURS;

        Long diffInMillis = 0L;

        logger.trace("getTimeLogInterval.oldDate = " + oldDate.getTime() + " newDate = "+ newDate.getTime());

        diffInMillis = newDate.getTime() - oldDate.getTime();

        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static Object[][] getTimeLogAsObjectArray(List<TimeLog> timeLogs) {

        Object[][] timeLogTableData = new Object[timeLogs.size()][];

        int i = 0;
        for (TimeLog timeLog : timeLogs) {
            logger.trace("setTimeLogTableData " + timeLog.getPerson().getName());
            long interval = TimeTrackerUtil.getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
            Object[] data = new Object[5];

            data[0] = timeLog.getPerson().getName();
            data[1] = timeLog.getLogDescription();
            data[2] = formatter.format(timeLog.getStartDateTime());
            data[3] = formatter.format(timeLog.getEndDateTime());
            data[4] = interval;

            timeLogTableData[i] = data;
            i++;
        }

        return timeLogTableData;
    }
}
