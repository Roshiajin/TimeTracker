package com.epam.view;

import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import com.epam.util.TimeTrackerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class TimeLogTableModel extends AbstractTableModel {

    private static final Logger logger = LogManager.getLogger(TimeLogTableModel.class);

    private String[] timeLogColumnNames = {"Person name",
            "Log Description",
            "Start DateTime",
            "End DateTime",
            "Interval"};

    private List<TimeLog> timeLogTableData = new ArrayList<TimeLog>();

    private TimeTrackerService service;

    public TimeLogTableModel(TimeTrackerService service) {
        this.service = service;
    }


    public long getTotalTime() {
        return service.getTotalTime(this.timeLogTableData);
    }

    public void setTimeLogTableData(List<TimeLog> tableData) {

        logger.trace("setTimeLogTableData: tableData.size = "+tableData.size());
        this.timeLogTableData = tableData;
    }

    @Override
    public int getColumnCount() {
        return this.timeLogColumnNames.length;
    }

    @Override
    public int getRowCount() {
        return timeLogTableData.size();
    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        TimeLog timeLog = null;
        timeLog = timeLogTableData.get(row);

        switch (column) {

            case 0:
                return timeLog.getPerson().getName();
            case 1:
                return timeLog.getLogDescription();
            case 2:
                return timeLog.getStartDateTime();
            case 3:
                return timeLog.getEndDateTime();
            case 4:
                return TimeTrackerUtil.getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
            default:
                return "";
        }
    }

    public String getColumnName(int col) {
        return timeLogColumnNames[col];
    }

}
