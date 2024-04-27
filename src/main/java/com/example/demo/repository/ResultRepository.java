package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {
    Result findResultById(Integer resultId);
    List<Result> findResultByReportId(Integer reportId);
}
