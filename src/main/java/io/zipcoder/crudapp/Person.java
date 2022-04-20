package io.zipcoder.crudapp;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    String firstName;
    String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Person(){}
    public Person(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
}
