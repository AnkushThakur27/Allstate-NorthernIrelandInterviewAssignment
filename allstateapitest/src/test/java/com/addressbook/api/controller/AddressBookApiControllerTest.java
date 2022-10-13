package com.addressbook.api.controller;

import com.addressbook.api.model.Person;
import com.addressbook.api.repository.PersonRepository;
import com.addressbook.api.service.PersonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Ankush Thakur
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class AddressBookApiControllerTest {

    @Mock
    PersonRepository personRepository;

    private static final Long ID = 1L;

    private  Person person ;

    @Test
    public void addPersonData() {
        person = Person.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .addresses(Arrays.asList("Dublin,Ireland","Pune,India"))
                .phoneNum(Arrays.asList("894594370","899751414"))
                .build();
        when(personRepository.save(person)).thenReturn(person);
    }

    @Test
    public void getPersonDataById() {
        person = Person.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Fadatare")
                .addresses(Arrays.asList("Dublin,Ireland","Pune,India"))
                .phoneNum(Arrays.asList("894594370","899751414"))
                .build();
        personRepository.save(person);
        Optional<Person> person1 = personRepository.findById(1L);
        when(personRepository.findById(1L)).thenReturn(person1);
    }

    @DisplayName("JUnit test for update Person operation")
    @Test
    public void updatePersonsDataById() {
        //Given
        Person person = givenPersonData();
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);
        //When
        //Then
        assertEquals(person, personRepository.save(person));
    }
    @Test
    public void should_return_null_update_person() {
        //Given
        Person person = givenPersonData();
        when(personRepository.findById(person.getId())).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(personRepository.save(person));
    }

    @Test
    public void should_delete_person_by_id() {
        //Given
        Person person = givenPersonData();
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        //When
        doNothing().when(personRepository).deleteById(ID);
    }

    @Test
    public void should_find_basic_by_id() {
        Person person1 = givenPersonData();
        when(personRepository.findById(ID)).thenReturn(Optional.of(person1));
    }
    @Test
    public void should_return_null_delete_person_by_id() {
        //Given
        Person person1 = givenPersonData();
        when(personRepository.findById(person1.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(personRepository).deleteById(ID);
    }
   @Test
    public void should_return_null_find_person_by_id() {
        when(personRepository.findById(ID)).thenReturn(Optional.empty());
    }

    private Person givenPersonData() {
        Person  person = Person.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Fadatare")
                .addresses(Arrays.asList("Dublin,Ireland","Pune,India"))
                .phoneNum(Arrays.asList("894594370","899751414"))
                .build();
        return person;
    }
}