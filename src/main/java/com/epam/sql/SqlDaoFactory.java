package com.epam.sql;

import com.epam.dao.DaoFactory;
import com.epam.dao.GenericDao;
import com.epam.dao.PersistException;
import com.epam.model.Person;
import com.epam.model.TimeLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class SqlDaoFactory implements DaoFactory<Connection> {

    private String user = "root";//Логин пользователя
    private String password = "";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:32769/timetracker";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    private Map<Class, DaoCreator> creators;

    public void getDaoProperties(){
        //todo: properties from dao.properties resource file
    }

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public SqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Person.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new PersonDao(SqlDaoFactory.this, connection);
            }
        });
        creators.put(TimeLog.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new TimeLogDao(SqlDaoFactory.this, connection);
            }
        });
    }
}
