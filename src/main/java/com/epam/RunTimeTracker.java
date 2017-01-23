package com.epam;

import com.epam.service.TimeTrackerService;
import com.epam.util.TimeTrackerUtil;
import com.epam.view.TimeLogTableModel;
import com.epam.view.TimeTrackerGUI;

/**
 * Created by Alexander_Gaptullin on 1/9/2017.
 */
public class RunTimeTracker {

    public RunTimeTracker() {

        TimeTrackerService service = new TimeTrackerService();

        String[] timeLogColumnNames = {"Person name",
                "Log Description",
                "Start DateTime",
                "End DateTime",
                "Interval"};

        //TimeLogTableModel tableModel = new TimeLogTableModel(TimeTrackerUtil.getTimeLogAsObjectArray(service.gettAllTimeLog()), timeLogColumnNames);
        TimeLogTableModel tableModel = new TimeLogTableModel(service.gettAllTimeLog(), timeLogColumnNames);

        new TimeTrackerGUI(service, tableModel);

    }
}
