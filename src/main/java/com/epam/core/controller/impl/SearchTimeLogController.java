package com.epam.core.controller.impl;

import com.epam.core.controller.Controller;
import com.epam.persistence.model.Person;
import com.epam.persistence.model.TimeLog;
import com.epam.persistence.services.DatabaseService;
import com.epam.presentation.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchTimeLogController implements Controller {

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        String personName = this.formService.getFilter();

        Person person = this.databaseService.retrieveByField("name", personName, Person.class);

        List<TimeLog> timeLogs = this.databaseService.retrieveByForeignKey("person_id", person.getId(), TimeLog.class);

        this.formService.update(timeLogs);

    }
}
