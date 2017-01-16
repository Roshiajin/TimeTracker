package com.epam.view;

import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import com.epam.util.TimeTrackerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TimeLogTableModel extends AbstractTableModel {

    private static final Logger logger = LogManager.getLogger(TimeLogTableModel.class);

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    private String[] timeLogColumnNames = {"Person name",
            "Log Description",
            "Start DateTime",
            "End DateTime",
            "Interval"};

    private Object[][] timeLogTableData;

    public TimeLogTableModel() {}

    public TimeLogTableModel(List<TimeLog> tableData) {

        setTimeLogTableData(tableData);

    }

    public void setTimeLogTableData(List<TimeLog> tableData) {
        this.timeLogTableData = new Object[tableData.size()][getColumnCount()];

        for (int i = 0; i < tableData.size(); i++) {
            logger.trace("setTimeLogTableData " + tableData.get(i).getPerson().getName());
            long interval = TimeTrackerUtil.getTimeLogInterval(tableData.get(i).getStartDateTime(), tableData.get(i).getEndDateTime());
            //Object[] data = {tableData.get(i).getPerson().getName(), tableData.get(i).getLogDescription(), formatter.format(tableData.get(i).getStartDateTime()), formatter.format(tableData.get(i).getEndDateTime()), interval};

            Object[] data = new Object[getColumnCount()];

            data[0] = tableData.get(i).getPerson().getName();
            data[1] = tableData.get(i).getLogDescription();
            data[2] = formatter.format(tableData.get(i).getStartDateTime());
            data[3] = formatter.format(tableData.get(i).getEndDateTime());
            data[4] = interval;

            timeLogTableData[i] = data;

        }
    }

    public int getColumnCount() {
        return timeLogColumnNames.length;
    }

    public int getRowCount() {
        return timeLogTableData.length;
    }

    public String getColumnName(int col) {
        return timeLogColumnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return timeLogTableData[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
