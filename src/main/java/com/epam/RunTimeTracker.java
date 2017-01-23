package com.epam;

import com.epam.controller.TimeLogController;
import com.epam.service.TimeTrackerService;
import com.epam.view.TimeLogTableModel;
import com.epam.view.TimeTrackerGUI;

/**
 * Created by Alexander_Gaptullin on 1/9/2017.
 */
public class RunTimeTracker {

    public RunTimeTracker() {

        TimeTrackerService service = new TimeTrackerService();

        TimeLogTableModel tableModel = new TimeLogTableModel(service);

        TimeTrackerGUI view = new TimeTrackerGUI(tableModel);

        TimeLogController controller = new TimeLogController(service);

        controller.addModel(tableModel);
        controller.addView(view);
        controller.initModel(service.gettAllTimeLog());

        view.addController(controller);

    }
}
