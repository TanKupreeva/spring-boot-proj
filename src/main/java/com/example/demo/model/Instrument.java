package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "instruments")
public class Instrument implements Comparable<Instrument> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String model;
    @Column(name = "factory_number")
    private String factoryNumber;
    @Column(name = "certificate_of_verification")
    private String certificateOfVerification;
    @Column(name = "valid_until")
    private Date validUntil;

    @ManyToMany(mappedBy = "instruments")
    private List<Report> reports;

    @Override
    public int compareTo(Instrument o) {
        return this.name.compareTo(o.getName());
    }
}
