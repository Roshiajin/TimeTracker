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

import java.util.List;

import static com.epam.utilities.Constants.ComponentTitles.MESSAGE_FAILURE;
import static com.epam.utilities.Constants.Messages.MSG_PERSON_NOT_FOUND;

@Component("SearchTimeLogController")
public class SearchTimeLogController implements Controller {

    private static final Logger logger = LogManager.getLogger(SearchTimeLogController.class);

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        String personName = this.formService.getFilter();

        Person person = null;
        List<TimeLog> timeLogs = null;
        if (personName.isEmpty()) {
            timeLogs = this.databaseService.retrieveAll(TimeLog.class);
        } else {

            person = this.databaseService.retrieveByField("name", personName, Person.class);

            if (person != null) {
                timeLogs = this.databaseService.retrieveByForeignKey("person", person.getId(), TimeLog.class);
                logger.trace("timeLogs.size = " + timeLogs.size());
                this.formService.update(timeLogs);
            } else {
                this.formService.showMessage(MSG_PERSON_NOT_FOUND, MESSAGE_FAILURE);
            }
        }

    }
}
