package com.addressbook.api.controller;

import com.addressbook.api.model.Person;
import com.addressbook.api.repository.PersonRepository;
import com.addressbook.api.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ankush Thakur
 */
@RestController
@RequestMapping("/api/v1/person-address-book/")
public class AddressBookApiController {

    Logger logger = LoggerFactory.getLogger(AddressBookApiController.class);

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;


    @RequestMapping(value = "/addPersonData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Person> addPersonData(@Valid @RequestBody Person person) {
        logger.info("addPersonData Method calling");
        try {
            personService.save(person);
            logger.info("Person Data Has been Saved");
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("Person Data is not valid");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/findPersonDataById/{id}")
    public ResponseEntity<Person> getPersonDataById(@PathVariable Long id) {
        logger.info("getPersonDataById Method calling");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with the given Id does not exist :" + id));
        return ResponseEntity.ok(person);
    }

    @PutMapping("/updatePersonsDataById/{id}")
    public ResponseEntity<Person> updatePersonsDataById(@PathVariable Long id, @RequestBody Person personDetails){
        logger.info("updatePersonsDataById Method calling");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with the given Id does not exist :" + id));
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setAddresses(personDetails.getAddresses());
        person.setPhoneNum(personDetails.getPhoneNum());
        Person updatedPersonsData = personService.save(person);
        logger.info("Person Data Updated");
        return ResponseEntity.ok(updatedPersonsData);
    }

    @DeleteMapping("/deletePersonsDataById/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePersonsData(@PathVariable Long id){
        logger.info("deletePersonsData Method calling");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with the given Id does not exist :" + id));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logger.info("Person Data Deleted");
        return ResponseEntity.ok(response);
    }

}
