package com.example.demo.service;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Grs;
import com.example.demo.model.Umg;

import java.util.List;
import java.util.Set;

public interface GrsService {
    String addGrs(Grs grs);

    List<Grs> getAllGrses();

    String updateGrs(Integer id, Grs grs);

    String deleteGrs(Integer id);
    Grs findGrsById(Integer id);
    Set<Grs> findBy(String start);
}
