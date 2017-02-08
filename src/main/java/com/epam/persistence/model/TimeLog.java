package com.epam.persistence.model;

import com.epam.utilities.annotations.Viewed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TimeLog")
public class TimeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @JoinColumn(nullable = false)
    @Viewed(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @Viewed(name = "Person")
    private Person person;

    @JoinColumn(nullable = false)
    @Viewed(name = "LogDescription")
    private String logDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Viewed(name = "StartDateTime")
    private java.util.Date startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Viewed(name = "EndDatetime")
    private java.util.Date endDatetime;

    public TimeLog() { }

    public TimeLog(Person person, String logDescription, Date startDateTime, Date endDatetime) {
        this.person = person;
        this.logDescription = logDescription;
        this.startDateTime = startDateTime;
        this.endDatetime = endDatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public java.util.Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(java.util.Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public java.util.Date getEndDateTime() {
        return endDatetime;
    }

    public void setEndDatetime(java.util.Date endDatetime) {
        this.endDatetime = endDatetime;
    }
}
