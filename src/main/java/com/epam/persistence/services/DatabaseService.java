package com.epam.persistence.services;

import java.util.List;

public interface DatabaseService {
    void create(Object object);
    void delete(int id, Class type);
    void update(Object object);
    <T> List<T> retrieveAll(Class<T> type);
    <T> T retrieveById(int id, Class<T> type);
    <T> T retrieveByField(String fieldName,String value, Class<T> type);
    <T> List<T> retrieveByForeignKey(String fieldName, int value,Class<T> type);
}
