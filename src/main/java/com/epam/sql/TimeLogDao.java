package com.epam.sql;

import com.epam.dao.AbstractDao;
import com.epam.dao.DaoFactory;
import com.epam.dao.PersistException;
import com.epam.model.Person;
import com.epam.model.TimeLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class TimeLogDao extends AbstractDao<TimeLog, Integer> {

    private static final Logger logger = LogManager.getLogger(TimeLogDao.class);

    public TimeLogDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(TimeLog.class, "person");
    }

    @Override
    public TimeLog create() throws PersistException {

        TimeLog timeLog = new TimeLog();
        return persist(timeLog);
    }

    @Override
    public String getSelectQuery() {
        return "select id, person_id, log_description, start_datetime, end_datetime from timelog ";
    }

    @Override
    public String getCreateQuery() {
        return "insert into timelog (person_id, log_description, start_datetime, end_datetime) values (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "update timelog set person_id = ?, log_description = ?, start_datetime = ?, end_datetime = ? where id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from timelog where id = ?";
    }

    @Override
    protected List<TimeLog> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<TimeLog> result = new LinkedList<TimeLog>();
        try {
            while (rs.next()) {
                TimeLog timeLog = new TimeLog();
                timeLog.setId(rs.getInt("id"));
                timeLog.setPerson((Person) getDependence(Person.class, rs.getInt("person_id")));
                timeLog.setLogDescription(rs.getString("log_description"));
                timeLog.setStartDateTime(rs.getTimestamp("start_datetime"));
                timeLog.setEndDateTime(rs.getTimestamp("end_datetime"));
                result.add(timeLog);
            }
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TimeLog object) throws PersistException {

        try {
            int personId = (object.getPerson() == null || object.getPerson().getId() == null) ? -1
                    : object.getPerson().getId();

            statement.setInt(1, personId);
            statement.setString(2, object.getLogDescription());
            statement.setTimestamp(3, new Timestamp(object.getStartDateTime().getTime()));
            statement.setTimestamp(4, new Timestamp(object.getEndDateTime().getTime()));

            logger.trace("prepareStatementForInsert.startDateTime " + object.getStartDateTime().getTime());
            logger.trace("prepareStatementForInsert.endDateTime " +  object.getEndDateTime().getTime());

        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TimeLog object) throws PersistException {
        try {
            Date startDateTime = convert(object.getStartDateTime());
            Date endDateTime = convert(object.getEndDateTime());

            statement.setInt(1, object.getPerson().getId());
            statement.setString(2, object.getLogDescription());
            statement.setDate(3, startDateTime);
            statement.setDate(4, endDateTime);
            statement.setInt(5, object.getId());

        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }

    }

    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    public List<TimeLog> getByPerson(Person person) throws PersistException {
        List<TimeLog> list;
        String sql = getSelectQuery();
        sql += " WHERE person_id = ?";
        logger.trace("getByPerson: sql = " + sql);
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            logger.trace("getByPerson: person_id = " + person.getId());

            statement.setInt(1, person.getId());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.catching(e);
            throw new PersistException(e);
        }
        return list;
    }
}
