package com.epam.sql;

import com.epam.dao.AbstractDao;
import com.epam.dao.DaoFactory;
import com.epam.dao.PersistException;
import com.epam.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class PersonDao extends AbstractDao<Person, Integer> {

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
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Person object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Person object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
