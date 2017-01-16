package com.epam.model;

import com.epam.dao.Identified;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by Alexander_Gaptullin on 12/19/2016.
 */
public class Person implements Identified<Integer> {

    private static final Logger logger = LogManager.getLogger(Person.class);

    private Integer id;
    private String name;

    public Person() {
        Random random = new Random();

        this.setName("PersonTempName"+ String.valueOf(random.nextInt()));
    }

    public Person(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
