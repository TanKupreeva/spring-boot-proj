package com.example.demo.service;

import com.example.demo.dto.CriteriaDto;
import com.example.demo.model.Criteria;
import com.example.demo.model.Mark;
import com.example.demo.repository.CriteriaRepository;
import com.example.demo.repository.MarkRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriteriaServiceImpl implements CriteriaService {

    @Resource
    CriteriaRepository criteriaRepository;

    @Resource
    MarkRepository markRepository;

    @Override
    public String addCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria) != null ? "Критерия добавлена успешно" : "Критерия не добавлена!";
    }

    @Override
    public List<Criteria> getAllCriterias() {
      return criteriaRepository.findAll();

    }

    @Override
    public String updateCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria) != null ? "Критерия изменена успешно" : "Критерия не изменена!";
    }

    @Override
    public String deleteCriteria(Integer id) {
        Optional<Criteria> criteria = criteriaRepository.findById(id);
        if (criteria.isPresent()) {
            criteriaRepository.deleteById(id);
            return "Критерия удалена успешно!";
        }
        return "Критерия не удалена!";
    }

    @Override
    public Criteria findCriteriaById(Integer id) {
//        List<Criteria> criterias = getAllCriterias();
//        for (Criteria c : criterias) {
//            if (c.getId().equals(id))
//                return c;
//        }
//        return null;
        return criteriaRepository.findCriteriaById(id);
    }




//
//
//    private Instrument mapDtoToEntity(InstrumentDto instrumentDto) {
//        Instrument instrument = new Instrument();
//        instrument.setName(instrumentDto.getName());
//        instrument.setModel(instrumentDto.getModel());
//        instrument.setFactoryNumber(instrumentDto.getFactoryNumber());
//        instrument.setCertificateOfVerification(instrumentDto.getCertificateOfVerification());
//        instrument.setValidUntil(instrumentDto.getValidUntil());
//        return instrument;
//    }
}
