package com.example.demo.service;

import com.example.demo.model.Condition;

import java.util.List;

public interface ConditionService {
    String  addCondition(Condition condition);

    List<Condition> getAllConditions();

    String updateCondition(Integer conditionId, Condition condition);

    String deleteCondition(Integer conditionId);
    List<Condition> findByReportId(Integer reportId);
    Condition findById(Integer id);

}
