package com.epam.model;

import com.epam.dao.Identified;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by Alexander_Gaptullin on 12/19/2016.
 */
public class TimeLog implements Identified<Integer> {

    private static final Logger logger = LogManager.getLogger(TimeLog.class);

    private Integer id;
    private Person person;
    private String logDescription;
    private Date startDateTime;
    private Date endDateTime;

    public TimeLog() {
        Date date = new Date();
        this.startDateTime = date;
        this.endDateTime = date;
    }

    public TimeLog(Person person, String logDescription, Date startDateTime, Date endDatetime) {
        this.person = person;
        this.logDescription = logDescription;
        this.startDateTime = startDateTime;
        this.endDateTime = endDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "TimeLog{" +
                "id=" + id +
                ", person=" + person +
                ", logDescription='" + logDescription + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDatetime=" + endDateTime +
                '}';
    }
}
