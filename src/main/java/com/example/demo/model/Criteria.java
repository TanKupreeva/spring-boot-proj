package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "criterias")
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String position;
    private String parameter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "excellent")
    private Mark excellent;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "good")
    private Mark good;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acceptable")
    private Mark acceptable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requires_action")
    private Mark requiresAction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unacceptable")
    private Mark unacceptable;



}
