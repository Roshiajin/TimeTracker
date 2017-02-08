package com.epam.persistence.access.impl;

import com.epam.persistence.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDAO extends DAOImpl {

    @PersistenceContext
    private EntityManager manager;

    public Person findByName(String name)
    {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), params));

        TypedQuery<Person> query = manager.createQuery(criteriaQuery);
        query.setParameter(params, name);

        List<Person> queryResult = query.getResultList();

        Person returnObject = null;

        if (!CollectionUtils.isEmpty(queryResult))
            returnObject = queryResult.get(0);

            return returnObject;
    }


}
