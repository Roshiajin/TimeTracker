package com.epam.service;

import com.epam.dao.DaoFactory;
import com.epam.dao.PersistException;
import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.sql.PersonDao;
import com.epam.sql.SqlDaoFactory;
import com.epam.sql.TimeLogDao;
import com.epam.util.TimeTrackerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TimeTrackerService {

    private static final Logger logger = LogManager.getLogger(TimeTrackerService.class);

    private static final DaoFactory<Connection> factory = new SqlDaoFactory();

    private Connection connection;

    private PersonDao personDao;

    private TimeLogDao timeLogDao;

    public TimeTrackerService() {
        try {
            this.connection = factory.getContext();
            this.personDao = (PersonDao) factory.getDao(connection, Person.class);
            this.timeLogDao = (TimeLogDao) factory.getDao(connection, TimeLog.class);
        } catch (PersistException e) {
            logger.catching(e);
        }

    }

    public List<TimeLog> gettAllTimeLog () {

        List<TimeLog> list = new LinkedList<>();

        try {
            list = timeLogDao.getAll();
        } catch (PersistException e) {
            logger.catching(e);
        }

        return list;
    }

    public Person getPersonByName(String personName) {

        Person person = null;

        logger.trace("service.getPersonNyName", personName);

        try {
            person = personDao.getByName(personName);

            logger.trace("service.getPersonNyName", person.toString());
        } catch (PersistException e) {
            logger.catching(e);
        }

        return person;
    }

    public void createTimeLog (String personName, String logDescription, Date startDateTime, Date endDateTime) {

        logger.trace("createTimeLog.personName " + personName);

        Person person;
        TimeLog timeLog;
        try {
            person = personDao.getByName(personName);

            if (person == null) {
                person = personDao.persist(new Person(personName));
            }

            timeLog = new TimeLog(person, logDescription, startDateTime, endDateTime);

            timeLog = timeLogDao.persist(timeLog);

        } catch (PersistException e) {
            logger.catching(e);
        }

    }

    public List<TimeLog> getPersonTimeLog(Person person) {

        List<TimeLog> personTimeLog = new LinkedList<>();

        try {
            if (person == null) {
                personTimeLog = timeLogDao.getAll();
            } else {
                personTimeLog = timeLogDao.getByPerson(person);
            }
        } catch (PersistException e) {
            logger.catching(e);
        }

        return personTimeLog;
    }

    public long getTotalTime(List<TimeLog> timeLogs) {

        Long totalTimeInHours = 0L;

        for (TimeLog timeLog : timeLogs) {


            logger.trace("getTotalTime timeLog " + timeLog.toString());
            totalTimeInHours += TimeTrackerUtil.getTimeLogInterval(timeLog.getStartDateTime(), timeLog.getEndDateTime());
            logger.trace("getTotalTime foreach " + totalTimeInHours);
        }

        logger.trace("getTotalTime = " + totalTimeInHours);

        return totalTimeInHours;
    }


}
