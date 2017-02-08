package com.epam.presentation.access;

import com.epam.persistence.model.Person;
import com.epam.persistence.model.TimeLog;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public interface FormAccessObject {
    String getPersonName();
    void setPersonName(String name);
    String getLogDescription();
    Date getStartDateTime();
    Date getEndDateTime();
    String getFilter();
    void setFilter(final String name);
    void writeTimeLog(final List<TimeLog> timeLog);
    void setTotalTime(final String totalTime);
    void showMessage(String message, String title);
    void showForm();
    void addButtonActionListener(ActionListener listener, String buttonName);
}