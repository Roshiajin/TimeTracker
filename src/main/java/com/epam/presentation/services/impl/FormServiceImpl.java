package com.epam.presentation.services.impl;

import com.epam.persistence.entities.TimeLog;
import com.epam.presentation.services.FormService;
import com.epam.utilities.calculations.TimeLogCalculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.presentation.access.FormAccessObject;
import com.epam.utilities.Constants;
import com.epam.utilities.exceptions.FormValidationException;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import static com.epam.utilities.Constants.Messages.MSG_PERSON_REQUIRED;

@Component
public class FormServiceImpl implements FormService {

    private static final Logger logger = LogManager.getLogger(FormServiceImpl.class);

    @Autowired
    private FormAccessObject formAccessObject;

    public String getPersonName() {
        String personName = this.formAccessObject.getPersonName();

        if (personName.isEmpty()) {
            showMessage(MSG_PERSON_REQUIRED, Constants.ComponentTitles.MESSAGE_ERROR);
            throw new FormValidationException(Constants.Errors.ERR_PERSON_REQUIRED);
        }

        return personName;
    }

    public void setPersonName(String name) { this.formAccessObject.setPersonName(name); }

    public String getLogDescription() { return this.formAccessObject.getLogDescription(); }

    public Date getStartDateTime() { return this.formAccessObject.getStartDateTime(); }

    public Date getEndDateTime() { return this.formAccessObject.getEndDateTime();}

    public String getFilter() { return this.formAccessObject.getFilter(); }

    public void setFilter(String name) { this.formAccessObject.setFilter(name); }

    public void writeTimeLog(final List<TimeLog> timeLog) {
        this.formAccessObject.writeTimeLog(timeLog);
    }

    public void setTotalTime(final String totalTime) {
        this.formAccessObject.setTotalTime(totalTime);
    }

    public void showMessage(String message, String title) {
        this.formAccessObject.showMessage(message, title);
    }

    public void showForm() {
        this.formAccessObject.showForm();
    }

    public void addButtonActionListener(ActionListener listener, String buttonName) {
        this.formAccessObject.addButtonActionListener(listener, buttonName);
    }

    public void update(final List<TimeLog> timeLog) {

        logger.trace("update timeLog.size = " + timeLog.size());

        this.writeTimeLog(timeLog);
        this.setTotalTime(String.valueOf(TimeLogCalculation.getTotalTime(timeLog)));
    }

    public void setLogDescription(String logDescription) {
        this.formAccessObject.setLogDescription(logDescription);
    }
}
