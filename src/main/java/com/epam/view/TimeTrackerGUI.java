package com.epam.view;

import com.epam.controller.TimeLogController;
import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TimeTrackerGUI extends MainGUI {

    private static final Logger logger = LogManager.getLogger(TimeTrackerGUI.class);

    private TimeTrackerService service;

    public TimeTrackerGUI(TimeTrackerService service, TimeLogTableModel tableModel) {

        super("TimeTracker", tableModel);
        this.service = service;

        setTotalTime(String.valueOf( service.getTotalTime( service.gettAllTimeLog() )));
    }

    public void addController(TimeLogController controller) {
        addCreateTimeLogListener(controller.getCreateTimeLogEventListener());
        addFilterListener(controller.getFilterEventListener());
        addFilterClearListener(controller.getFilterClearEventListener());
    }

    void update(List<TimeLog> timeLogs) {

        getTableModel().setTimeLogTableData(timeLogs);
        getTableModel().fireTableDataChanged();
        setTotalTime(String.valueOf(service.getTotalTime(timeLogs)));
    }

}
