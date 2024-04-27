package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    private String name;
    @Column(name = "relation_from")
    private String relationFrom;
    @Column(name = "relation_to")
    private String relationTo;
    @Column(name = "mark_from")
    private String markFrom;
    @Column(name = "mark_to")
    private String markTo;
}
