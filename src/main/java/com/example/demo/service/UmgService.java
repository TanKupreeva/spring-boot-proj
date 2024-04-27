package com.example.demo.service;

import com.example.demo.model.Office;
import com.example.demo.model.Umg;

import java.util.List;

public interface UmgService {
    String addUmg(Umg umg);

    List<Umg> getAllUmges();

    String updateUmg(Integer id, Umg umg);

    String deleteUmg(Integer id);
    Umg findUmgById(Integer id);
}
