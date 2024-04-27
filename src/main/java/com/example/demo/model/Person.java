package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person implements Comparable<Person>{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    private String name;

    @Column(name = "first_name")
    private String firstName;

    private String function;

    private String certificate;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String token;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "persons_roles",
            joinColumns = {@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "persons")
    private List<Report> reports;

    @Override
    public int compareTo(Person o) {
        return this.lastName.compareTo(o.getLastName());
    }
}
