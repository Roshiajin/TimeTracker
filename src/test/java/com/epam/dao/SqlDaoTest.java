package com.epam.dao;

import com.epam.model.Person;
import com.epam.model.TimeLog;
import com.epam.sql.SqlDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class SqlDaoTest extends GenericDaoTest<Connection> {

    private Connection connection;

    private GenericDao dao;

    private static final DaoFactory<Connection> factory = new SqlDaoFactory();

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                {Person.class, new Person()},
                {TimeLog.class, new TimeLog()}
        });
    }

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = factory.getContext();
        connection.setAutoCommit(false);
        dao = factory.getDao(connection, daoClass);
    }

    @After
    public void tearDown() throws SQLException {
        context().rollback();
        context().close();
    }

    @Override
    public GenericDao dao() {
        return dao;
    }

    @Override
    public Connection context() {
        return connection;
    }

    public SqlDaoTest(Class modelClass, Identified<Integer> notPersistedDto) {
        super(modelClass, notPersistedDto);
    }
}
