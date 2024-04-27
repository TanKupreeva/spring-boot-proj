package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.model.DetailResult;
import com.example.demo.model.Grs;
import com.example.demo.repository.DetailResultsRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetailResultsServiseImpl implements DetailResultsService {

    @Resource
    DetailResultsRepository detailResultsRepository;


    @Override
    public String addDetailResults(DetailResult detailResult) {
        return detailResultsRepository.save(detailResult) != null ? "Результат добавлен успешно" : "Результат не добавлен!";
    }

    @Override
    public String updateDetailResults(Integer id, DetailResult detailResult) {
        DetailResult dr = findById(id);
        detailResultsRepository.save(detailResult);
        return dr != null ? "Результат изменен успешно" : "Результат не изменен!";
    }


    @Override
    public List<DetailResult> getAllDetailResultsByResultId(Integer resultId) {
//        List<DetailResult> all = detailResultsRepository.findAll();
//        List<DetailResult> list = new ArrayList<>();
//        for (DetailResult dr : all) {
//            if (dr.getResult().getId().equals(resultId))
//                list.add(dr);
//        }
//        return list;
        return detailResultsRepository.findByResult_Id(resultId);
    }

    @Override
    public List<Double> listV(Integer resultid) {
        List<DetailResult> all = detailResultsRepository.findByResult_Id(resultid);
        List<Double> list = new ArrayList<>();
        for (DetailResult dr : all) {
            list.add(dr.getV());
        }
        return list;
    }

    @Override
    public List<Double> listP(Integer resultId) {
        List<DetailResult> all = detailResultsRepository.findByResult_Id(resultId);
        List<Double> list = new ArrayList<>();
        for (DetailResult dr : all) {
            list.add(dr.getP());
        }
        return list;
    }

    @Override
    public List<Double> listO(Integer resultId) {
        List<DetailResult> all = detailResultsRepository.findByResult_Id(resultId);
        List<Double> list = new ArrayList<>();
        for (DetailResult dr : all) {
            list.add(dr.getO());
        }
        return list;
    }

    @Override
    public List<Integer> listPointByCriteriaAndReport(Integer criteriaId, Integer reportId) {
//       List<DetailResult> detailResultList =  detailResultsRepository.findByCriteria_Id(criteriaId);
//        List<Integer> integerList = new ArrayList<>();
//        for (DetailResult dr:detailResultList) {
//            if(dr.getResult().getReport().getId().equals(reportId)){
//                integerList.add(dr.getT());
//            }
//        }
        List<DetailResult> detailResultList = detailResultsRepository.findByCriteria_IdAndResultId(criteriaId, reportId);
        List<Integer> listPoint = new ArrayList<>();
        for (DetailResult dr:detailResultList) {

           listPoint.add(dr.getT());
        }
        Collections.sort(listPoint);
        return listPoint;
    }

    @Override
    public List<DetailResult> findByResultId(Integer id) {
        return detailResultsRepository.findByResult_Id(id);
    }

    @Override
    public Set<Criteria> findByIdResult(Integer resultId) {
        List<DetailResult> detailResults = detailResultsRepository.findByResult_Id(resultId);
        Set<Criteria> criteriaSet = new HashSet<>();
        for (DetailResult dr: detailResults) {
            criteriaSet.add(dr.getCriteria());
        }
        return criteriaSet;
    }

    @Override
    public DetailResult findById(Integer id) {
        return detailResultsRepository.findDetailResultById(id);
    }


}
