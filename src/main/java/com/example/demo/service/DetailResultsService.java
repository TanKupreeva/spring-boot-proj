package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.model.DetailResult;
import com.example.demo.model.Grs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DetailResultsService {
    String addDetailResults(DetailResult detailResult);

    String updateDetailResults(Integer id, DetailResult detailResult);


    List<DetailResult> getAllDetailResultsByResultId(Integer resultId);

    List<Double> listV(Integer resultId);

    List<Double> listP(Integer resultId);

    List<Double> listO(Integer resultId);

    List<Integer> listPointByCriteriaAndReport(Integer criteriaId, Integer reportId);

    List<DetailResult> findByResultId(Integer id);

    Set<Criteria> findByIdResult( Integer resultId);
    DetailResult findById(Integer id);
}
