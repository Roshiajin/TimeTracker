package com.epam.persistence.services.impl;

import com.epam.persistence.access.DAO;
import com.epam.persistence.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DAO dataAccessObject;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void create(Object object) {
        dataAccessObject.save(object);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(int id, Class type) {
        Object object = this.dataAccessObject.retrieveById(id, type);
        this.dataAccessObject.delete(object);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void update(Object object) {
        this.dataAccessObject.update(object);
    }

    public <T> List<T> retrieveAll(Class<T> type) {
        List<T> result = this.dataAccessObject.retrieveAll(type);
        return result;
    }

    public <T> T retrieveById(int id, Class<T> type) {
        T result = this.dataAccessObject.retrieveById(id, type);
        return result;
    }

    public <T> T retrieveByField(String fieldName,String value, Class<T> type) {
        T result = this.dataAccessObject.retrieveByField(fieldName, value, type);
        return result;
    }

    public <T> List<T> retrieveByForeignKey(String fieldName, int value,Class<T> type) {
        List<T> result = this.dataAccessObject.retrieveByForeignKey(fieldName, value, type);
        return result;
    }

    public void setDataAccessObject(DAO dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }
}
