package com.example.demo.repository;

import com.example.demo.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria,Integer> {
    Criteria findCriteriaById(Integer criteriaId);

}
