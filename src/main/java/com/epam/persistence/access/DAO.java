package com.epam.persistence.access;

import java.util.List;

public interface DAO {
    void save(Object object);
    <T> void delete(Object object);
    void update(Object object);
    <T> List<T> retrieveAll(Class<T> type);
    <T> T retrieveById(int id, Class<T> type);
    <T> T retrieveByField(String fieldName,String value, Class<T> type);
    <T> List<T> retrieveByForeignKey(String fieldName, int value,Class<T> type);
}
