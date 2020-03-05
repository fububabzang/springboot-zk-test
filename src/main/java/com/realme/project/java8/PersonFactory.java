package com.realme.project.java8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public interface PersonFactory<P extends Person> {

    P create(String firstName, String lastName);
}

class PersonTest {

    /** logger */
    private static final Logger logger = LogManager.getLogger(PersonTest.class);

    public static void main(String[] args) {

        PersonFactory<Person> personFactory = Person :: new;
        Person person = personFactory.create("张三", "李四");
        System.out.println(person);
        logger.info("Person 对象是: {}" , person);



    }


}
