package com.epam.persistence.access.impl;

import com.epam.persistence.model.Person;
import com.epam.persistence.model.TimeLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class TimeLogDAO extends DAOImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<TimeLog> findByPerson(Person person)
    {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<TimeLog> criteriaQuery = criteriaBuilder.createQuery(TimeLog.class);
        Root<TimeLog> root = criteriaQuery.from(TimeLog.class);
        criteriaQuery.select(root);

        ParameterExpression<Integer> params = criteriaBuilder.parameter(Integer.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("person_id"), params));

        TypedQuery<TimeLog> query = manager.createQuery(criteriaQuery);
        query.setParameter(params, person.getId());

        List<TimeLog> queryResult = query.getResultList();

        return queryResult;
    }
}
