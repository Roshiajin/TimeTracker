package com.epam.persistence.access.impl;

import org.springframework.stereotype.Repository;
import com.epam.persistence.access.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DAOImpl implements DAO {

    @PersistenceContext
    private EntityManager manager;

    public void save(Object object) {
        manager.persist(object);
    }

    public void delete(Object object) {
        manager.remove(object);
    }

    public void update(Object object) {
        manager.merge(object);
    }

    public <T> List<T> retrieveAll(Class<T> type) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(type);
        Root o = query.from(type);
        query.select(o);

        List resultList = manager.createQuery(query).getResultList();
        return resultList;
    }

    public <T> T retrieveById(int id, Class<T> type) {
        T result = manager.find(type, id);
        return result;
    }

    public <T> T retrieveByField(String fieldName,String value, Class<T> type) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(type);
        Root from = criteriaQuery.from(type);
        criteriaQuery.select(from);

        criteriaQuery.where(criteriaBuilder.equal(from.get(fieldName), value));
        TypedQuery typed = manager.createQuery(criteriaQuery);
        try {
            return (T) typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public <T> List<T> retrieveByForeignKey(String fieldName, int value,Class<T> type) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(type);
        Root from = criteriaQuery.from(type);
        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get(fieldName), value));
        TypedQuery typed = manager.createQuery(criteriaQuery);
        try {
            return typed.getResultList();
        } catch (final NoResultException nre) {
            return null;
        }
    }
}
