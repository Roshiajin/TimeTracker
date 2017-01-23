package com.epam.view;

import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TimeTrackerGUI extends ExampleGUI {

    private static final Logger logger = LogManager.getLogger(TimeTrackerGUI.class);

    private TimeTrackerService service;

    private TimeLogWorker worker;

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
        getTableModel().fireTableDataChanged();
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

                worker = new TimeLogWorker("");
                worker.execute();

                setPersonName("");
                setLogDescription("");

            }
        }
    }

    class FilterEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            worker = new TimeLogWorker(getFilter());
            worker.execute();

        }
    }

    class FilterClearEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            setFilter("");

            worker = new TimeLogWorker("");
            worker.execute();

        }
    }

    class TimeLogWorker extends SwingWorker<List, String> {

        String filter;

        TimeLogWorker(String filter) {
          this.filter = filter;
        }

        @Override
        protected List<TimeLog> doInBackground() throws Exception {
            List<TimeLog> list;

            //System.out.println("Start TimeLogSwingWorker");
            logger.trace("Start TimeLogSwingWorker");

//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {}

            String personName = this.filter;

            if (personName == null || personName.isEmpty()) {
                list = service.gettAllTimeLog();
                logger.trace("End TimeLogSwingWorker: list.size="+list.size());
                //System.out.println("End TimeLogSwingWorker");
                return list;
            }

            Person person = service.getPersonByName(personName);

            if (person == null) {
                logger.trace("End TimeLogSwingWorker: list is null");
                //System.out.println("End TimeLogSwingWorker");
                return null;
            }

            list = service.getPersonTimeLog(person);

            logger.trace("End TimeLogSwingWorker: list.size="+list.size());
            //System.out.println("End TimeLogSwingWorker");

            return list;
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<TimeLog> list = get();
                        if (list == null) {
                            showMessage("No such person name found!","Error!");
                        } else {
                            update(list);
                        }

                    } catch (Exception ex) {
                        logger.catching(ex);
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }


}
