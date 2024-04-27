package com.example.demo.service;

import com.example.demo.dto.ReportDto;
//import com.example.demo.model.Image;
import com.example.demo.model.Report;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ReportService {
    String addReport(Report report) throws IOException;

    List<ReportDto> getAllReports();

    ReportDto updateReport(Integer reportDtoId, ReportDto reportDto);

    String deleteReport(Integer reportId);
    Set<ReportDto> findBy(String start);
    Report findById(Integer id);

}
