package com.epam.persistence.services;


import com.epam.persistence.access.DAO;
import com.epam.persistence.entities.Person;
import com.epam.persistence.entities.TimeLog;
import com.epam.persistence.services.impl.DatabaseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//Load Spring contexte
@ContextConfiguration(locations = {"classpath:/spring/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseServiceTest {

    private static final int TEST_PERSON_ID = 1;
    private static final String TEST_PERSON_NAME = "Test";

    @InjectMocks
    private DatabaseServiceImpl databaseService;

    @Mock
    @Autowired
    private DAO dataAccessObject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);
    }

    @Test
    public void testCreate() {

        Person person = new Person("Test1");

        databaseService.create(person);

        verify(dataAccessObject, times(1)).save(person);
    }

    @Test
    public void testUpdate() {

        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);
        person.setPhoneNumber("111");

        databaseService.update(person);

        verify(dataAccessObject, times(1)).update(person);
    }

    @Test
    public void testDelete() {

        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);

        when(dataAccessObject.retrieveById(person.getId(), Person.class)).thenReturn(person);

        databaseService.delete(person.getId(), Person.class);

        verify(dataAccessObject, times(1)).delete(person);
    }

    @Test
    public void testRetrieveById() {

        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);

        when(dataAccessObject.retrieveById(TEST_PERSON_ID, Person.class)).thenReturn(person);

        Person retrievedPerson = databaseService.retrieveById(TEST_PERSON_ID, Person.class);

        assertEquals(person, retrievedPerson);
    }

    @Test
    public void testRetrieveAll() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Person1"));
        personList.add(new Person("Person2"));

        when(dataAccessObject.retrieveAll(Person.class)).thenReturn(personList);

        List<Person> retrievedPersons = databaseService.retrieveAll(Person.class);

        assertEquals(personList, retrievedPersons);

    }

    @Test
    public void testRetrieveByField() {

        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);

        when(dataAccessObject.retrieveByField("name", TEST_PERSON_NAME, Person.class)).thenReturn(person);

        Person retrievedPerson = databaseService.retrieveByField("name", TEST_PERSON_NAME, Person.class);

        assertEquals(person, retrievedPerson);
    }

    @Test
    public void testRetrieveByForeignKey() {

        Person person = new Person(TEST_PERSON_NAME);
        person.setId(TEST_PERSON_ID);

        List<TimeLog> timeLogList = new ArrayList<>();
        timeLogList.add(new TimeLog(person, "test desc1", new Date(), new Date()));
        timeLogList.add(new TimeLog(person, "test desc2", new Date(), new Date()));

        when(dataAccessObject.retrieveByForeignKey("person", person.getId(), TimeLog.class)).thenReturn(timeLogList);

        List<TimeLog> retrievedTimeLogList = databaseService.retrieveByForeignKey("person", person.getId(), TimeLog.class);

        assertEquals(timeLogList, retrievedTimeLogList);

    }














}
