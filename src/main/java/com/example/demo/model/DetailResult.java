package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "detail_results")
public class DetailResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "result_id")
    private Result result;

    private Integer t;
    private Double v;
    private Double p;
    private Double o;

    @ManyToOne()
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

}
