package com.epam.presentation.access.impl;

import com.epam.persistence.entities.TimeLog;
import com.epam.presentation.access.FormAccessObject;
import com.epam.presentation.view.MainGUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

@Component
public class FormAccessObjectImpl implements FormAccessObject {

    private static final Logger logger = LogManager.getLogger(FormAccessObjectImpl.class);

    @Autowired
    private MainGUI form;

    public String getPersonName() { return this.form.getPersonName(); }

    public void setPersonName(String name) { this.form.setPersonName(name); }

    public String getPhoneNumber() { return this.form.getPhoneNumber(); }

    public void setPhoneNumber(String phoneNumber) { this.form.setPhoneNumber(phoneNumber); }

    public String getLogDescription() { return this.form.getLogDescription(); }

    public Date getStartDateTime() { return this.form.getStartDateTime(); }

    public Date getEndDateTime() { return this.form.getEndDateTime();}

    public String getFilter() { return this.form.getFilter(); }

    public void setFilter(String name) { this.form.setFilter(name); }

    public void writeTimeLog(final List<TimeLog> timeLog) {
        logger.trace("writeTimeLog:  this.form.getTimeLogTable().getModel().getRowCount() = "+ this.form.getTimeLogTable().getModel().getRowCount());

        this.form.getTimeLogTable().getModel().getRowCount();
        this.form.getTableModel().setTimeLogTableData(timeLog);

        this.form.getTimeLogTable().setModel(this.form.getTableModel());

    }

    public void setTotalTime(final String totalTime) {
        this.form.setTotalTime(totalTime);
    }

    public void showMessage(String message, String title) {
        this.form.showMessage(message, title);
    }

    public void showForm() {
        form.show();
    }

    public void addButtonActionListener(ActionListener listener, String buttonName) {
        final JButton button = this.form.getButton(buttonName);
        button.addActionListener(listener);
    }

    public void setLogDescription(String logDescription) {
        this.form.setLogDescription(logDescription);
    }
}
