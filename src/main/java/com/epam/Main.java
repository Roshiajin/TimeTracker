package com.epam;

import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import com.epam.util.TimeTrackerUtil;
import com.epam.view.TimeLogTableModel;
import com.epam.view.TimeTrackerGUI;

import javax.swing.table.TableModel;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexander_Gaptullin on 12/19/2016.
 */
public class Main {

    public static void main( String[] args )
    {

        System.out.println( "Welcome to TimeTracker!" );

        TimeTrackerService service = new TimeTrackerService();

        TableModel tableModel = new TimeLogTableModel(service.gettAllTimeLog());

//        List<TimeLog> tableData = service.gettAllTimeLog();
//
//
//        Object[][] timeLogTableData;
//
//        System.out.println(tableData.size());
//
//        timeLogTableData = new Object[tableData.size()][];
//
//        for (int i = 0; i < tableData.size(); i++) {
//            System.out.println(tableData.get(i).getPerson().getName());
//            long interval = TimeTrackerUtil.getTimeLogInterval(tableData.get(i).getStartDateTime(), tableData.get(i).getEndDateTime());
//            Object[] data = {tableData.get(i).getPerson().getName(), tableData.get(i).getLogDescription(), tableData.get(i).getStartDateTime(), tableData.get(i).getEndDateTime(), interval};
//            timeLogTableData[i] = data;
//        }


        new TimeTrackerGUI(service, tableModel);
    }
}
