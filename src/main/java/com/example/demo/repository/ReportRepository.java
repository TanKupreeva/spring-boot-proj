package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    Report findReportById(Integer id);

}
