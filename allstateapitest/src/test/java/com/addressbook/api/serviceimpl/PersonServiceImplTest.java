package com.addressbook.api.serviceimpl;

import com.addressbook.api.model.Person;
import com.addressbook.api.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Ankush Thakur
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class PersonServiceImplTest {
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonServiceImpl personService;

    @Test
    public void save() {
        Person person = givenPersonData();
        person.setId(0);
        when(personRepository.save(person)).thenReturn(person);
        //When
        //Then
        assertEquals(person, personService.save(person));
    }

    private Person givenPersonData() {
        Person  person = Person.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .addresses(Arrays.asList("Dublin,Ireland","Pune,India"))
                .phoneNum(Arrays.asList("894594370","899751414"))
                .build();
        return person;
    }

}