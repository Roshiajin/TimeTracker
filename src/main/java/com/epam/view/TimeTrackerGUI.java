package com.epam.view;

import com.epam.controller.TimeLogController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeTrackerGUI extends MainGUI {

    private static final Logger logger = LogManager.getLogger(TimeTrackerGUI.class);

    public TimeTrackerGUI(TimeLogTableModel tableModel) {

        super("TimeTracker", tableModel);

        setTotalTime(String.valueOf(getTableModel().getTotalTime()));
    }

    public void addController(TimeLogController controller) {
        logger.trace("addController");
        addCreateTimeLogListener(controller.getCreateTimeLogEventListener());
        addFilterListener(controller.getFilterEventListener());
        addFilterClearListener(controller.getFilterClearEventListener());
    }

    public void update() {
        logger.trace("update()");
        getTableModel().fireTableDataChanged();
        setTotalTime(String.valueOf(getTableModel().getTotalTime()));
    }

}
