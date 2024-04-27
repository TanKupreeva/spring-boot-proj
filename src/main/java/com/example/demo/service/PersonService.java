package com.example.demo.service;

//import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import com.example.demo.model.Report;

import java.util.List;

public interface PersonService {


    String addPerson(Person person);

    List<Person> getAllPersons();

    String updatePerson(Integer personId, Person person);

    String deletePerson(Integer personDtoId);
    Person findById(Integer id);
    Person findByToken(String token);



//    void savePerson(PersonDto personDto);
    Person findPersonByEmail(String email);
//    List<PersonDto> findAllPersons();


}
