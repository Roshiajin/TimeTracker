package com.epam.presentation.view;

import com.epam.persistence.entities.TimeLog;
import com.epam.utilities.Constants;
import com.epam.utilities.calculations.TimeLogCalculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeLogTableModel extends AbstractTableModel {

    private static final Logger logger = LogManager.getLogger(TimeLogTableModel.class);

    private static final String[] timeLogColumnNames = Constants.TableModelNames.columnNames;

    private List<TimeLog> timeLogTableData = new ArrayList<TimeLog>();

    public void setTimeLogTableData(List<TimeLog> tableData) {

        logger.trace("setTimeLogTableData: tableData.size = "+tableData.size());
        this.timeLogTableData = tableData;
        this.fireTableDataChanged();
        logger.trace("setTimeLogTableData: this.getColumnCount() = "+this.getColumnCount());
        logger.trace("setTimeLogTableData: this.getRowCount() = "+this.getRowCount());
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
                return TimeLogCalculation.getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
            default:
                return "";
        }
    }

    public String getColumnName(int col) {
        return timeLogColumnNames[col];
    }

}
