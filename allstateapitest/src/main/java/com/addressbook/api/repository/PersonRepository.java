package com.addressbook.api.repository;

import com.addressbook.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ankush Thakur
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
}
