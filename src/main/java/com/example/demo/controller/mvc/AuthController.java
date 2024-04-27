package com.example.demo.controller.mvc;

//import com.example.demo.dto.PersonDto;
import com.example.demo.model.Office;
import com.example.demo.model.Person;
import com.example.demo.model.Role;
import com.example.demo.service.MailService;
import com.example.demo.service.PersonService;
import com.example.demo.service.RoleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private PersonService personService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;
    @Autowired
    PasswordEncoder passwordEncoder;

//    public AuthController(PersonService personService) {
//        this.personService = personService;
//    }

    @GetMapping("/register/forget")
    public String forget() {
        return "forget";
    }






    @PostMapping("/register/forget/{email}")
    public String forget(@RequestParam(name = "email") String email, Model model) {
        Person person = personService.findPersonByEmail(email.trim());
        String msg = "Вы не являетесь сотрудником нашей организации. Проверьте правильность введенного пароля";
        if (person != null) {
            String token = UUID.randomUUID().toString();
            String url = "http://localhost:8088/register/update?token=" + token;
            person.setToken(token);
            personService.updatePerson(person.getId(), person);
            mailService.sendNewMail(email, "Сброс пароля", "Перейдите по ссылке " + url + " для сброса пароля");
            msg = "Проверьте свою почту";
        }
        model.addAttribute("msg", msg);
        return "msg";
    }

    @GetMapping("/register/update")
    public String updatePassword(Model model, @RequestParam String token) {
        System.out.println(token);
        Person person = personService.findByToken(token);
        model.addAttribute("token",token);
        String msg;
        if (person != null) {
            return "update_password";
        } else {
            msg = "Ссылка не действительна";
            model.addAttribute("msg", msg);
            return "msg";
        }
    }


    @PostMapping("/register/update")
    public String update(@RequestParam(name = "psw1") String password1, @RequestParam(name = "token") String token, Model model) {
        System.out.println(token);
        Person person = personService.findByToken(token);
        person.setActive(true);
        person.setPassword(passwordEncoder.encode(password1));
        personService.updatePerson(person.getId(), person);
        String msg = "Пароль успешно изменен";
        model.addAttribute("msg", msg);
        return "login";
    }

    // handler method to handle home page request
//    @GetMapping("/index")
//    public String home() {
//        return "index";
//    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    // handler method to handle user registration form request
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//// create model object to store form data
//        PersonDto person = new PersonDto();
//        model.addAttribute("person", person);
//
//        return "register";
//    }
//
//    // handler method to handle user registration form submit request
//    @PostMapping("/register/save")
//    public String registration(@Valid @ModelAttribute("person") PersonDto personDto,
//                               BindingResult result,
//                               Model model) {
//        Person existingPerson = personService.findPersonByEmail(personDto.getEmail());
//        if (existingPerson != null && existingPerson.getEmail() != null && !existingPerson.getEmail().isEmpty()) {
//            result.rejectValue("email", null,
//                    "Вы уже активированы");
//        }
//        if (result.hasErrors()) {
//            model.addAttribute("person", personDto);
//            return "/register";
//        }
//        personService.savePerson(personDto);
//        return "redirect:/register?success";
//    }
//
//    // handler method to handle list of users
//    @GetMapping("/persons")
//    public String persons(Model model) {
//        List<PersonDto> persons = personService.findAllPersons();
//        model.addAttribute("persons", persons);
//        return "persons";
//    }
//
//    @GetMapping("/persons/delete/{id}")
//    public String deletePerson(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
//        String msg = personService.deletePerson(id);
//        redirectAttributes.addFlashAttribute("msg", msg);
//        return "redirect:/persons";
//    }
//
//    @GetMapping("/persons/edit/{id}")
//    public String editPerson(@PathVariable(name = "id") Integer id, Model model) {
//        Person person = personService.findById(id);
//        model.addAttribute("person", person);
//        List<Role> roles = roleService.getAllRoles();
//        model.addAttribute("roles", roles);
//        return "edit_person";
//    }
//
//    @PostMapping("/persons/edit")
//    public String updatePerson(@ModelAttribute("person") Person person, Model model,
//                               RedirectAttributes redirectAttributes) {
//        String msg = personService.updatePerson(person.getId(), person);
//        redirectAttributes.addFlashAttribute("msg", msg);
//
//        return "redirect:/persons";
//    }

}