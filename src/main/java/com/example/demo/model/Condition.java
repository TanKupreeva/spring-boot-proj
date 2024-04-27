package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "conditions")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "report_id")
    private Report report;

    private String place;
    private Date date;
    private Double temperature;
    private Double humidity;
    private String sensor;
    private String surfaceCondition;


}
