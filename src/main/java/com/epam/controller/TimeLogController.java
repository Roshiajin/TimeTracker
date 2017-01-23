package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.service.TimeTrackerService;
import com.epam.view.TimeLogTableModel;
import com.epam.view.TimeTrackerGUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TimeLogController {

    private static final Logger logger = LogManager.getLogger(TimeLogController.class);

    private TimeLogTableModel model;
    private TimeTrackerGUI view;
    private TimeTrackerService service;
    private TimeLogWorker worker;

    public TimeLogController(TimeTrackerService service) {
        this.service = service;
    }

    public void addModel(TimeLogTableModel model) {
        logger.trace("Adding controller model");
        this.model = model;
    }

    public void addView(TimeTrackerGUI view) {
        logger.trace("Adding controller view");
        this.view = view;
    }

    public void initModel(List modelData) {
        model.setTimeLogTableData(modelData);
    }

    void update(List<TimeLog> timeLogs) {

        view.getTableModel().setTimeLogTableData(timeLogs);
        view.getTableModel().fireTableDataChanged();
        view.setTotalTime(String.valueOf(service.getTotalTime(timeLogs)));
    }

    public CreateTimeLogEventListener getCreateTimeLogEventListener() {
        return new CreateTimeLogEventListener();
    }

    public FilterEventListener getFilterEventListener() {
        return new FilterEventListener();
    }

    public FilterClearEventListener getFilterClearEventListener() {
        return new FilterClearEventListener();
    }

    class CreateTimeLogEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "CreateButton was pressed\n";
            message += "values: \n" + view.getPersonName() + "\n"
                    + view.getLogDescription() + "\n"
                    + view.getStartDateTime().toString() + "\n"
                    + view.getEndDateTime().toString();

            logger.trace(message);

            if (view.getPersonName()==null || view.getPersonName().isEmpty()) {

                view.showMessage("Person name is empty!", "Error!");

            } else {
                logger.trace("createActionPerformed: " + view.getPersonName());

                try {
                    service.createTimeLog(view.getPersonName(), view.getLogDescription(), view.getStartDateTime(), view.getEndDateTime());

                    view.showMessage("Time log added!", "Operation success!");

                } catch (Exception ex) {
                    logger.catching(ex);
                    view.showMessage("Check logs for error!", "Operation failed!");
                }

                worker = new TimeLogWorker("");
                worker.execute();

                view.setPersonName("");
                view.setLogDescription("");

            }
        }
    }

    class FilterEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            worker = new TimeLogWorker(view.getFilter());
            worker.execute();

        }
    }

    class FilterClearEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            view.setFilter("");

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

            logger.trace("Start TimeLogSwingWorker");

            String personName = this.filter;

            if (personName == null || personName.isEmpty()) {
                list = service.gettAllTimeLog();
                logger.trace("End TimeLogSwingWorker: list.size="+list.size());

                return list;
            }

            Person person = service.getPersonByName(personName);

            if (person == null) {
                logger.trace("End TimeLogSwingWorker: list is null");

                return null;
            }

            list = service.getPersonTimeLog(person);

            logger.trace("End TimeLogSwingWorker: list.size="+list.size());

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
                            view.showMessage("No such person name found!","Error!");
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
