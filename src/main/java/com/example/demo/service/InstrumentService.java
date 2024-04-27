package com.example.demo.service;

import com.example.demo.dto.InstrumentDto;
import com.example.demo.dto.ReportDto;
import com.example.demo.model.Instrument;

import java.util.List;
import java.util.Set;

public interface InstrumentService {

    String addInstrument(Instrument instument);

    Instrument findInstrumentById(Integer instrumentId);
    List<Instrument> getAllInstruments();


    String updateInstrument(Integer instrumentId, Instrument instrument);

    String deleteInstrument(Integer instrumentId);
    Set<Instrument> findBy(String start);

}
