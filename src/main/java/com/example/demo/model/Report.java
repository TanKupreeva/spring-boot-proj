package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grs_id")
    private Grs grs;

    @Column(name = "registration_num")
    private String registrationNum;

    private Date date;

    @Column(name = "test_date")
    private Date testDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
//    private Client client;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "report_person",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private List<Person> persons;


    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "report_instrument", joinColumns = {@JoinColumn(name = "report_id")}, inverseJoinColumns = {
            @JoinColumn(name = "instrument_id")})
    private List<Instrument> instruments;

    private String scheme;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "report")
    private List<Condition>conditions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "report")
    private List<Result>results;

}
