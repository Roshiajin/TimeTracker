package com.epam.view;

import com.epam.service.TimeTrackerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimeTrackerGUI extends ExampleGUI {

    private static final Logger logger = LogManager.getLogger(TimeTrackerGUI.class);

    TimeTrackerService service;

    public TimeTrackerGUI(TimeTrackerService service, TableModel tableModel) {

        super("TimeTracker", tableModel);
        addCreateTimeLogListener(new CreateTimeLogEventListener());
        addFilterListener(new FilterEventListener());
        this.service = service;

        setTotalTime(String.valueOf( service.getTotalTime( service.gettAllTimeLog() )));
    }

    class CreateTimeLogEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "CreateButton was pressed\n";
            message += "values: \n" + getPersonName() + "\n"
                    + getLogDescription() + "\n"
                    + getStartDateTime().toString() + "\n"
                    + getEndDateTime().toString();

            JOptionPane.showMessageDialog(null,
                    message,
                    "Output",
                    JOptionPane.PLAIN_MESSAGE);

            if (getPersonName()==null || getPersonName().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Person name is empty!",
                        "Error!",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                service.createTimeLog(getPersonName(), getLogDescription(), getStartDateTime(), getEndDateTime());
            }
        }
    }

    class FilterEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "FilterButton was pressed\n";
            message += "value: " + getFilter();

            JOptionPane.showMessageDialog(null,
                    message,
                    "Output",
                    JOptionPane.PLAIN_MESSAGE);




        }
    }


}
