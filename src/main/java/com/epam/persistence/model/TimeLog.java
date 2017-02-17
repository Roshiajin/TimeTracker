package com.epam.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "timelog")
public class TimeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "log_description", nullable = true)
    private String logDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_datetime", nullable = false)
    private java.util.Date startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_datetime", nullable = false)
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
