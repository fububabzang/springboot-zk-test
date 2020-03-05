package com.realme.project.java8;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-03 11:38
 **/
public class Person {

    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
