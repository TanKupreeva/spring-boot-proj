package com.example.demo.repository;

import com.example.demo.model.DetailResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailResultsRepository extends JpaRepository<DetailResult, Integer> {
    List<DetailResult> findByCriteria_Id(Integer criteriaId);

    List<DetailResult> findByResult_Id(Integer resultId);

    List<DetailResult> findByCriteria_IdAndResultId(Integer criteriaId, Integer resultId);
    DetailResult findDetailResultById(Integer detailResultId);

}
