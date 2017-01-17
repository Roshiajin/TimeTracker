package com.epam.view;

import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TimeTrackerGUI extends ExampleGUI {

    private static final Logger logger = LogManager.getLogger(TimeTrackerGUI.class);

    private TimeTrackerService service;

    public TimeTrackerGUI(TimeTrackerService service, TimeLogTableModel tableModel) {

        super("TimeTracker", tableModel);
        addCreateTimeLogListener(new CreateTimeLogEventListener());
        addFilterListener(new FilterEventListener());
        addFilterClearListener(new FilterClearEventListener());
        this.service = service;

        setTotalTime(String.valueOf( service.getTotalTime( service.gettAllTimeLog() )));
    }

    void update(List<TimeLog> timeLogs) {
        getTableModel().setTimeLogTableData(timeLogs);
        setTotalTime(String.valueOf(service.getTotalTime(timeLogs)));
    }

    class CreateTimeLogEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "CreateButton was pressed\n";
            message += "values: \n" + getPersonName() + "\n"
                    + getLogDescription() + "\n"
                    + getStartDateTime().toString() + "\n"
                    + getEndDateTime().toString();

            logger.trace(message);

            if (getPersonName()==null || getPersonName().isEmpty()) {

                showMessage("Person name is empty!", "Error!");

            } else {
                logger.trace("createActionPerformed: " + getPersonName());

                try {
                    service.createTimeLog(getPersonName(), getLogDescription(), getStartDateTime(), getEndDateTime());

                    showMessage("Time log added!", "Operation success!");

                } catch (Exception ex) {
                    logger.catching(ex);
                    showMessage("Check logs for error!", "Operation failed!");
                }

                update(service.gettAllTimeLog());
                setPersonName("");
                setLogDescription("");

            }
        }
    }

    class FilterEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String personName = getFilter();

            String message = "";
            message += "FilterButton was pressed\n";
            message += "value: " + personName;

            logger.trace(message);

            if (personName == null || personName.isEmpty()) {
                update(service.gettAllTimeLog());
                return;
            }

            Person person = service.getPersonByName(personName);

            if (person == null) {
                showMessage("No such person name found!","Error!");
                return;
            }

            update(service.getPersonTimeLog(person));

        }
    }

    class FilterClearEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            setFilter("");

            update(service.gettAllTimeLog());

        }
    }


}
