package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "report_id")
    private Report report;

    @Column(name = "number_n")
    private Integer numberN;
    @Column(name = "quantity_t")
    private Integer quantityT;

    @Column(name = "p_in")
    private Double pIn;
    @Column(name = "p_out")
    private Double pOut;
    private Double rate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "result")
    private List<DetailResult> detailResults;


}
