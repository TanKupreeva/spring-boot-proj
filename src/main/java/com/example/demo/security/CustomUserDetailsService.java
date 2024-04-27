package com.example.demo.security;

import com.example.demo.model.Person;
import com.example.demo.model.Role;
import com.example.demo.repository.PersonRepository;

import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private PersonRepository personRepository;
//    public CustomUserDetailsService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByEmail(email);
        if (person != null) {
            return new User(person.getEmail(),
                    person.getPassword(),
                    mapRolesToAuthorities(person.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}