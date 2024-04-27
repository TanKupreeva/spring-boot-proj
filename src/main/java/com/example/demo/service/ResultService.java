package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.model.Report;
import com.example.demo.model.Result;

import java.util.List;

public interface ResultService {
    String addResult(Result result);

    List<Result> getAllResultsByReport(Integer reportId);

    String updateResult(Integer id,Result result);

    String deleteResult(Integer id);

    Result findResultById(Integer id);
}
