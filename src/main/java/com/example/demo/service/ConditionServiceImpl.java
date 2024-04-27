package com.example.demo.service;

import com.example.demo.model.Condition;
import com.example.demo.repository.ConditionRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConditionServiceImpl implements ConditionService {


    @Resource
    ConditionRepository conditionRepository;


    @Override
    public String addCondition(Condition condition) {
        return conditionRepository.save(condition) != null ? "Условие проведения испытаний создано успешно" : "Условие проведения испытаний не создано";
    }

    @Override
    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    @Override
    public String updateCondition(Integer conditionId, Condition condition) {
        Condition c  = findById(conditionId);
        conditionRepository.save(condition);
        return c != null ? "Условие изменено успешно" : "Условие не изменено!";

    }

    @Override
    public String deleteCondition(Integer conditionId) {
        Optional<Condition> condition = conditionRepository.findById(conditionId);
        if (condition.isPresent()) {
            conditionRepository.deleteById(conditionId);
            return "Условие удалено успешно!";
        }
        return "Условие не удалено!";
    }

    @Override
    public List<Condition> findByReportId(Integer reportId) {
       return conditionRepository.findConditionByReportId(reportId);
    }

    @Override
    public Condition findById(Integer id) {
//        List<Condition> conditions = conditionRepository.findAll();
//        for (Condition c : conditions) {
//            if (c.getId().equals(id))
//                return c;
//        }
        return conditionRepository.findConditionById(id);
    }
}