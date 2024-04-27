package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.model.Office;
import com.example.demo.model.Report;
import com.example.demo.model.Result;
import com.example.demo.repository.ResultRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {


    @Resource
    ResultRepository resultRepository;

    @Override
    public String addResult(Result result) {
        return resultRepository.save(result) != null ? "Результат добавлен успешно" : "Результат не добавлен!";
    }

    @Override
    public List<Result> getAllResultsByReport(Integer reportId) {
//        List<Result> all = resultRepository.findAll();
//        List<Result> resultList = new ArrayList<>();
//        for (Result r : all) {
//            if (r.getReport().getId().equals(reportId))
//                resultList.add(r);
//        }
//        return resultList;
return resultRepository.findResultByReportId(reportId);
    }

    @Override
    public String updateResult(Integer id,Result result) {
        resultRepository.getReferenceById(id);
        Result r = resultRepository.save(result);
        return r != null ? "Данные изменены успешно" : "Данные не изменены!";

    }


    @Override
    public String deleteResult(Integer id) {
        Optional<Result> result = resultRepository.findById(id);
        if (result.isPresent()) {
            resultRepository.deleteById(result.get().getId());
            return "Данные удалены успешно!";
        }
        return "Данные не удалена!";
    }


    @Override
    public Result findResultById(Integer id) {
//        List<Result> resultList = resultRepository.findAll();
//        for (Result r : resultList
//        ) {
//            if (r.getId().equals(id))
//                return r;
//        }
//        return null;
        return resultRepository.findResultById(id);
    }
}
