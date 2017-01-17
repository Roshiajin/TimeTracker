package com.epam.view;

import com.epam.model.TimeLog;
import com.epam.util.TimeTrackerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.DefaultTableModel;
import java.util.List;


public class TimeLogTableModel extends DefaultTableModel {

    private static final Logger logger = LogManager.getLogger(TimeLogTableModel.class);

    private String[] timeLogColumnNames;

    private Object[][] timeLogTableData;

    public TimeLogTableModel(Object[][] tableData, String[] tableHeaders) {

        super(tableData, tableHeaders);
        this.timeLogTableData = tableData;
        this.timeLogColumnNames = tableHeaders;

    }

    public void setTimeLogTableData(List<TimeLog> tableData) {

        this.timeLogTableData = TimeTrackerUtil.getTimeLogAsObjectArray(tableData);

        setDataVector(this.timeLogTableData, this.timeLogColumnNames);
    }

}
