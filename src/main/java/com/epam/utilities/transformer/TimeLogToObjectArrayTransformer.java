package com.epam.utilities.transformer;

import com.epam.persistence.model.TimeLog;
import com.epam.utilities.calculations.TimeLogCalculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.epam.utilities.Constants.Formats.dateFormatter;

public class TimeLogToObjectArrayTransformer {

    private static final Logger logger = LogManager.getLogger(TimeLogToObjectArrayTransformer.class);

    public static Object[][] transform(List<TimeLog> timeLogs) {

        Object[][] timeLogTableData = new Object[timeLogs.size()][];

        int i = 0;
        for (TimeLog timeLog : timeLogs) {
            logger.trace("transform for " + timeLog.getPerson().getName());
            long interval = TimeLogCalculation.getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
            Object[] data = new Object[5];

            data[0] = timeLog.getPerson().getName();
            data[1] = timeLog.getLogDescription();
            data[2] = dateFormatter.format(timeLog.getStartDateTime());
            data[3] = dateFormatter.format(timeLog.getEndDateTime());
            data[4] = interval;

            timeLogTableData[i] = data;
            i++;
        }

        return timeLogTableData;
    }
}
