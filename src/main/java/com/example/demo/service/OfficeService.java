package com.example.demo.service;

import com.example.demo.model.Office;

import java.util.List;

public interface OfficeService {

    String addOffice(Office office);

    List<Office> getAllOffices();

    String updateOffice(Integer officeId, Office office);

    String deleteOffice(Integer officeId);
    Office findOfficeById(Integer id);
}
