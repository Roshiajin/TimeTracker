package com.epam.core.controller.impl;

import com.epam.core.controller.Controller;
import com.epam.persistence.model.TimeLog;
import com.epam.persistence.services.DatabaseService;
import com.epam.presentation.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClearSearchedController implements Controller {

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        this.formService.setFilter("");

        List<TimeLog> timeLogs = this.databaseService.retrieveAll(TimeLog.class);

        this.formService.update(timeLogs);

    }
}
