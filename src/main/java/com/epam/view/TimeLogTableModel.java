package com.epam.view;

import com.epam.model.TimeLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;


class TimeLogTableModel extends AbstractTableModel {

    private static final Logger logger = LogManager.getLogger(TimeLogTableModel.class);

    private String[] timeLogColumnNames = {"Person name",
            "Log Description",
            "Start DateTime",
            "End DateTime",
            "Interval"};

    private Object[][] timeLogTableData = {
            {"Kathy1", "Kathy log description", new Date(), new Date(), new Integer(1)},
            {"Kathy2", "Kathy log description", new Date(), new Date(), new Integer(2)},
            {"Kathy3", "Kathy log description", new Date(), new Date(), new Integer(3)},
            {"Kathy4", "Kathy log description", new Date(), new Date(), new Integer(4)},
            {"Kathy5", "Kathy log description", new Date(), new Date(), new Integer(5)},
            {"Kathy6", "Kathy log description", new Date(), new Date(), new Integer(6)}
    };

    public TimeLogTableModel() {}

    public TimeLogTableModel(List<TimeLog> tableData) {

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
