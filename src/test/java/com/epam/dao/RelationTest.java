package com.epam.dao;

import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.sql.SqlDaoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class RelationTest {
    private static final DaoFactory<Connection> factory = new SqlDaoFactory();

    private Connection connection;

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = factory.getContext();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testCreate() throws PersistException {
        TimeLog timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).create();
        Assert.assertNull("Person is not null.", timeLog.getPerson());

        Person person = new Person();
        timeLog.setPerson(person);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
    }

    @Test
    public void testPersist() throws PersistException {
        TimeLog timeLog = new TimeLog();
        Person person = (Person) factory.getDao(connection, Person.class).create();
        timeLog.setPerson(person);
        person.setName("Alex");
        timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).persist(timeLog);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
        Assert.assertEquals("Wrong person name.", "Alex", timeLog.getPerson().getName());
    }

    @Test
    public void testPersistAll() throws PersistException {
        TimeLog timeLog = new TimeLog();
        timeLog.setPerson(new Person());
        timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).persist(timeLog);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
        Assert.assertNotNull("Person.id is null.", timeLog.getPerson().getId());
    }

    @Test
    public void testUpdate() throws PersistException {
        TimeLog timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).create();

        timeLog.setPerson(new Person());
        factory.getDao(connection, TimeLog.class).update(timeLog);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
        Assert.assertNotNull("Person.id is null.", timeLog.getPerson().getId());
    }

    @Test
    public void testUpdateAll() throws PersistException {
        TimeLog timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).create();
        Person person = (Person) factory.getDao(connection, Person.class).create();
        person.setName("Alex");
        timeLog.setPerson(person);
        factory.getDao(connection, TimeLog.class).update(timeLog);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
        Assert.assertEquals("Wrong person name.", "Alex", timeLog.getPerson().getName());
    }

    @Test
    public void testRead() throws PersistException {
        TimeLog timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).create();
        timeLog.setPerson(new Person());
        factory.getDao(connection, TimeLog.class).update(timeLog);

        timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).getByPK(timeLog.getId());
        Assert.assertNotNull("Timelog is null.", timeLog);
        Assert.assertNotNull("Person is null.", timeLog.getPerson());
    }

    @Test
    public void testDelete() throws PersistException {
        TimeLog timeLog = (TimeLog) factory.getDao(connection, TimeLog.class).create();
        timeLog.setPerson(new Person());
        factory.getDao(connection, TimeLog.class).update(timeLog);

        Person person = timeLog.getPerson();

        factory.getDao(connection, TimeLog.class).delete(timeLog);
        person = (Person) factory.getDao(connection, Person.class).getByPK(person.getId());
        Assert.assertNotNull("Person not found.", person);
    }
}
