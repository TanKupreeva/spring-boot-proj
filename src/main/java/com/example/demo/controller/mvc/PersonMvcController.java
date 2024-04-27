package com.example.demo.controller.mvc;

//import com.example.demo.dto.PersonDto;

import com.example.demo.model.Person;
import com.example.demo.model.Role;
import com.example.demo.service.PersonService;
import com.example.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonMvcController {
    Logger logger = LoggerFactory.getLogger(PersonMvcController.class);

    @Autowired
    PersonService personService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new person");
        Person person = new Person();
        model.addAttribute("person", person);

        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("roles", roles);
        return "new_person";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("person") Person person, RedirectAttributes redirectAttributes) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        String msg = personService.addPerson(person);
        logger.info("Adding person {}", person);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/person/all";
    }

    @GetMapping("/all")
    public String getAllPersons(Model model) {
        logger.info("get all persons");

        List<Person> persons = personService.getAllPersons();
//        List<PersonDto> persons = personService.findAllPersons();
        model.addAttribute("persons", persons);
//        List<Role> roles = roleService.getAllRoles();
//        model.addAttribute("roles", roles);
        return "list_persons";
    }


    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable(name = "id") Integer id, Model model) {
        Person person = personService.findById(id);
        logger.info("Show form for edit person {}", person);
        person.setRoles(null);
        model.addAttribute("person", person);
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "edit_person";
    }

    @PostMapping("/edit")
    public String updatePerson(@ModelAttribute("person") Person person, Model model,
                               RedirectAttributes redirectAttributes) {
        String msg = personService.updatePerson(person.getId(), person);
        logger.info("Update person {}", person);

        redirectAttributes.addFlashAttribute("msg", msg);

        return "redirect:/person/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = "Невозможно удалить сотрудника";
        try {
            msg = personService.deletePerson(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);
            logger.info("Delete person id={}", id);
            return "redirect:/person/all";
        }

    }


}
