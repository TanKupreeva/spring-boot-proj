package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findPersonByEmail(String  email);
    Person findPersonById(Integer id);

    Person findPersonByToken(String token);

}
