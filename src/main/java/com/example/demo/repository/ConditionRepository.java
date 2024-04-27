package com.example.demo.repository;

import com.example.demo.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition,Integer> {
   List<Condition> findConditionByReportId(Integer reportId);

    Condition findConditionById(Integer id);
}
