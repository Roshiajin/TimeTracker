package com.epam.sql;

import com.epam.dao.AbstractDao;
import com.epam.dao.DaoFactory;
import com.epam.dao.PersistException;
import com.epam.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class PersonDao extends AbstractDao<Person, Integer> {

    private static final Logger logger = LogManager.getLogger(PersonDao.class);

    @Override
    public String getSelectQuery() {
        return "select id, name from person ";
    }

    @Override
    public String getCreateQuery() {
        return "insert into person(name) values (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "update person set name = ? where id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from person where id = ?;";
    }

    @Override
    public Person create() throws PersistException {
        Person person = new Person();
        return persist(person);
    }

    public PersonDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Person> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Person> result = new LinkedList<Person>();
        try {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                result.add(person);
            }
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Person object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Person object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
    }

    public Person getByName(String personName) throws PersistException {
        List<Person> list;
        String sql = getSelectQuery();
        sql += " WHERE name = ?";
        logger.trace("getByName: sql = " + sql);
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, personName);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            logger.trace("getByName.size", list.size());
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }
}
