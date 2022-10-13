package com.addressbook.api.serviceimpl;

import com.addressbook.api.model.Person;
import com.addressbook.api.repository.PersonRepository;
import com.addressbook.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ankush Thakur
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        personRepository.save(person);
        return person;
    }
}
