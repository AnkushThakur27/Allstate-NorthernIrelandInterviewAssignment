package com.addressbook.api;

import com.addressbook.api.model.Person;
import com.addressbook.api.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class ApiApplication {
    Logger logger = LoggerFactory.getLogger(ApiApplication.class);

    @Autowired
    PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
   @PostConstruct
    private void initDb() {
        logger.info("Data Insertion Started");
        Person person = new Person();
        person.setId(1);
        person.setFirstName("Ankush");
        person.setLastName("Thakur");
        person.setAddresses(Arrays.asList("Dublin,Ireland","Pune,India"));
        person.setPhoneNum(Arrays.asList("894594370","899751414"));
        repository.save(person);
        logger.info("Data Inserted");
    }
}
