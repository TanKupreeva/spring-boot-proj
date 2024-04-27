package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.repository.InstrumentRepository;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstrumentServiceImpl implements InstrumentService {


    @Resource
    InstrumentRepository instrumentRepository;


    @Override
    public String addInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument) != null ? "Средство измерения добавлено успешно" : "Средство измерения не добавлено";

    }

    @Override
    public Instrument findInstrumentById(Integer instrumentId) {
        return instrumentRepository.findInstrumentById(instrumentId);
    }


    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();

    }

    @Override
    @Modifying
    public String updateInstrument(Integer instrumentId, Instrument instrument) {
        List<Instrument> instruments = instrumentRepository.findAll();
        for (Instrument i : instruments) {
            if (i.getId().equals(instrumentId))
                return instrumentRepository.save(instrument) != null ? "Средство измерения изменено успешно" : "Средство измерения не изменено";
            ;
        }
        return null;

    }

    @Override
    public String deleteInstrument(Integer instrumentId) {
        List<Instrument> instruments = instrumentRepository.findAll();
        for (Instrument i : instruments) {
            if (i.getId().equals(instrumentId)) {
                instrumentRepository.delete(i);
                return "Средство измерения удалено успешно";
            }
        }
        return "Средство измерения не удалено";
    }




    @Override
    public Set<Instrument> findBy(String start) {
        List<Instrument> instruments = instrumentRepository.findAll();
        Set<Instrument> instrumentResults = new HashSet<>();
        for (Instrument i : instruments) {
            if (i.getName().toLowerCase().contains(start.toLowerCase()))
                instrumentResults.add(i);
            if (i.getFactoryNumber().toLowerCase().contains(start.toLowerCase()))
                instrumentResults.add(i);
            if (i.getModel().toLowerCase().contains(start.toLowerCase()))
                instrumentResults.add(i);
            if (i.getCertificateOfVerification().toLowerCase().contains(start.toLowerCase()))
                instrumentResults.add(i);
            if (i.getValidUntil().toString().toLowerCase().contains(start))
                instrumentResults.add(i);
        }
        return instrumentResults;
    }


}

