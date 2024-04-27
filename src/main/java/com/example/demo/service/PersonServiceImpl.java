package com.example.demo.service;

//import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import com.example.demo.model.Report;
import com.example.demo.model.Role;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonRepository personRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String addPerson(Person person) {
        return personRepository.save(person) != null ? "Работник добавлен успешно" : "Работник не добавлен!";
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public String updatePerson(Integer personId, Person person) {
        Person p = findById(personId);
        personRepository.save(person);

        return p != null ? " Данные работника изменены успешно" : "Данные работника не изменены!";
    }

    @Override
    public String deletePerson(Integer personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            personRepository.deleteById(personId);
            return "Работник удален успешно!";
        }
        return "Работник не удален!";
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findPersonById(id);
    }


//    public PersonServiceImpl(PersonRepository personRepository,
//                             RoleRepository roleRepository,
//                             PasswordEncoder passwordEncoder) {
//        this.personRepository = personRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

//    @Override
//    public void savePerson(PersonDto personDto) {
//        Person person = new Person();
//        person.setFirstName(personDto.getFirstName());
//        person.setLastName(personDto.getLastName());
//        person.setEmail(personDto.getEmail());
//        person.setCertificate(personDto.getCertificate());
//        person.setDateOfIssue(personDto.getDateOfIssue());
//        person.setId(personDto.getId());
//        person.setRoles(personDto.getRoles());
//        person.setFunction(personDto.getFunction());
//        person.setActive(personDto.isActive());
//
//
//// encrypt the password using spring security
//        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
//        Role role = roleRepository.findByName("ROLE_USER");
//        if (role == null) {
//            role = checkRoleExist();
//        }
//        person.setRoles(Arrays.asList(role));
//        personRepository.save(person);
//    }

    @Override
    public Person findPersonByEmail(String email) {
        return personRepository.findPersonByEmail(email);
    }

    @Override
    public Person findByToken(String token) {
        return personRepository.findPersonByToken(token);
    }


//    @Override
//    public List<PersonDto> findAllPersons() {
//        List<Person> persons = personRepository.findAll();
//        return persons.stream()
//                .map((person) -> mapToPersonDto(person))
//                .collect(Collectors.toList());
//    }


//    private PersonDto mapToPersonDto(Person person) {
//        PersonDto personDto = new PersonDto();
//        personDto.setFirstName(person.getFirstName());
//        personDto.setName(person.getName());
//        personDto.setLastName(person.getLastName());
//        personDto.setEmail(person.getEmail());
//        personDto.setCertificate(person.getCertificate());
//        personDto.setDateOfIssue(person.getDateOfIssue());
//        personDto.setId(person.getId());
//        personDto.setRoles(person.getRoles());
//        personDto.setFunction(person.getFunction());
//        personDto.setActive(person.isActive());
//        personDto.setReports(person.getReports());
//        return personDto;
//    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }


}

