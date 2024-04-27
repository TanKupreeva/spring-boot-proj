package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "grs")
public class Grs implements Comparable<Grs> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @Override
    public int compareTo(Grs g) {
        return this.name.compareTo(g.getName());
    }
}
