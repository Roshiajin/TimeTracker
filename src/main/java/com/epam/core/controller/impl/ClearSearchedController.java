package com.epam.core.controller.impl;

import com.epam.core.controller.Controller;
import com.epam.persistence.entities.TimeLog;
import com.epam.persistence.services.DatabaseService;
import com.epam.presentation.services.FormService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ClearSearchedController")
public class ClearSearchedController implements Controller {

    private static final Logger logger = LogManager.getLogger(ClearSearchedController.class);

    @Autowired
    private FormService formService;

    @Autowired
    private DatabaseService databaseService;

    public void perform() {

        this.formService.setFilter("");

        List<TimeLog> timeLogs = this.databaseService.retrieveAll(TimeLog.class);

        logger.trace("timeLogs.size = " + timeLogs.size());

        this.formService.update(timeLogs);

    }
}
