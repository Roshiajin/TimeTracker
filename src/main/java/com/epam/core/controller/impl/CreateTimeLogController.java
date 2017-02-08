package com.epam.core.controller.impl;

import com.epam.core.controller.Controller;
import com.epam.persistence.model.Person;
import com.epam.persistence.model.TimeLog;
import com.epam.persistence.services.DatabaseService;
import com.epam.presentation.services.FormService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Component
public class CreateTimeLogController implements Controller {

    private static final Logger logger = LogManager.getLogger(CreateTimeLogController.class);

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        String personName = this.formService.getPersonName();

        Person person = null;

        person = this.databaseService.retrieveByField("name", personName, Person.class);

        if (person == null) {
            person = new Person();
            person.setName(personName);
            this.databaseService.create(person);
            person = this.databaseService.retrieveByField("name", personName, Person.class);
        }

        String logDescription = this.formService.getLogDescription();

        Date startDateTime = this.formService.getStartDateTime();

        Date endDateTime = this.formService.getEndDateTime();

        TimeLog timeLog = new TimeLog(person, logDescription, startDateTime, endDateTime);

        this.databaseService.create(timeLog);

        List<TimeLog> timeLogs = this.databaseService.retrieveAll(TimeLog.class);

        this.formService.update(timeLogs);
    }

//
//    class TimeLogWorker extends SwingWorker<List, String> {
//
//        String filter;
//
//        TimeLogWorker(String filter) {
//            this.filter = filter;
//        }
//
//        @Override
//        protected List<TimeLog> doInBackground() throws Exception {
//            List<TimeLog> list;
//
//            logger.trace("Start TimeLogSwingWorker");
//
//            String personName = this.filter;
//
//            if (personName == null || personName.isEmpty()) {
//                list = service.gettAllTimeLog();
//                logger.trace("End TimeLogSwingWorker: list.size="+list.size());
//
//                return list;
//            }
//
//            Person person = service.getPersonByName(personName);
//
//            if (person == null) {
//                logger.trace("End TimeLogSwingWorker: list is null");
//
//                return null;
//            }
//
//            list = service.getPersonTimeLog(person);
//
//            logger.trace("End TimeLogSwingWorker: list.size="+list.size());
//
//            return list;
//        }
//
//        @Override
//        protected void done() {
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        List<TimeLog> list = get();
//                        if (list == null) {
//                            view.showMessage("No such person name found!","Error!");
//                        } else {
//                            initModel(list);
//                        }
//
//                    } catch (Exception ex) {
//                        logger.catching(ex);
//                        throw new RuntimeException(ex);
//                    }
//                }
//            });
//        }
//    }

}
