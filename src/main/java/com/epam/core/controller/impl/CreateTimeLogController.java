package com.epam.core.controller.impl;

import com.epam.core.controller.Controller;
import com.epam.persistence.entities.Person;
import com.epam.persistence.entities.TimeLog;
import com.epam.persistence.services.DatabaseService;
import com.epam.presentation.services.FormService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.epam.utilities.Constants.Messages.MSG_OPERATION_FAILED;
import static com.epam.utilities.Constants.Messages.MSG_PERSON_REQUIRED;

@Component("CreateTimeLogController")
public class CreateTimeLogController implements Controller {

    private static final Logger logger = LogManager.getLogger(CreateTimeLogController.class);

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        String personName = this.formService.getPersonName();

        Person person = null;

        if (personName.isEmpty()) {
            this.formService.showMessage(MSG_PERSON_REQUIRED, MSG_OPERATION_FAILED);
            return;
        }

        person = this.databaseService.retrieveByField("name", personName, Person.class);

        if (person == null) {
            person = new Person();
            person.setName(personName);
            person.setPhoneNumber(this.formService.getPhoneNumber());
            this.databaseService.create(person);
            person = this.databaseService.retrieveByField("name", personName, Person.class);
        }

        String logDescription = this.formService.getLogDescription();

        Date startDateTime = this.formService.getStartDateTime();

        Date endDateTime = this.formService.getEndDateTime();

        TimeLog timeLog = new TimeLog(person, logDescription, startDateTime, endDateTime);

        this.databaseService.create(timeLog);

        List<TimeLog> timeLogs = this.databaseService.retrieveAll(TimeLog.class);

        logger.trace("timeLogs.size = " + timeLogs.size());

        this.formService.setPersonName("");
        this.formService.setPhoneNumber("");
        this.formService.setLogDescription("");

        this.formService.update(timeLogs);
    }

}
