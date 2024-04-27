package com.example.demo.service;

import com.example.demo.dto.CriteriaDto;
import com.example.demo.model.Criteria;
import com.example.demo.model.Grs;
import com.example.demo.model.Mark;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriteriaService {
    String addCriteria(Criteria criteria);

    List<Criteria> getAllCriterias();

    String updateCriteria(Criteria criteria);

    String deleteCriteria(Integer id);

    Criteria findCriteriaById(Integer id);


}
