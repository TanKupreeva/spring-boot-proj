package com.example.demo.service;

//import com.example.demo.dto.PersonDto;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    ReportRepository reportRepository;
    @Resource
    ConditionRepository conditionRepository;

    @Resource
    ResultRepository resultRepository;

    @Resource
    DetailResultsRepository detailResultsRepository;

    @Resource
    PersonService personService;


    @Override
    public String addReport(Report report) throws IOException {
        return reportRepository.save(report) != null ? "Протокол добавлен успешно" : "Протокол не добавлен!";
    }


    @Override
    public List<ReportDto> getAllReports() {
        List<ReportDto> reportDtos = new ArrayList<>();
        List<Report> reports = reportRepository.findAll();
        for (Report r : reports) {
            reportDtos.add(mapEntityToDto(r));
        }
        return reportDtos;
    }

    @Override
    public ReportDto updateReport(Integer reportDtoId, ReportDto reportDto) {
        return null;
    }

    @Override
    public String deleteReport(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            reportRepository.deleteById(reportId);
            return "Протокол удален успешно!";
        }
        return "Протокол не удален!";
    }


    @Override
    public Set<ReportDto> findBy(String start) {
        Set<ReportDto> reportDtos = new HashSet<>();
        for (ReportDto r : getAllReports()) {
            if (r.getGrs().getName().toLowerCase().contains(start.toLowerCase()))
                reportDtos.add(r);
            if (r.getRegistrationNum().contains(start))
                reportDtos.add(r);
            List<String> persons = r.getFioPersons();
            for (String p : persons) {
                if (p.toLowerCase().contains(start.toLowerCase())) {
                    reportDtos.add(r);
                }
            }
            if (r.getGrs().getName().toLowerCase().contains(start.toLowerCase()))
                reportDtos.add(r);

        }
        return reportDtos;
    }

    @Override
    public Report findById(Integer id) {
        return reportRepository.findReportById(id);
    }


    public String formatDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru")));

    }


    public String formatTestDate(String testDateString) {
        LocalDate date = LocalDate.parse(testDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));
    }


    private ReportDto mapEntityToDto(Report r) {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(r.getId());
        reportDto.setGrs(r.getGrs());
        reportDto.setRegistrationNum(r.getRegistrationNum());
        reportDto.setDate(formatDate(r.getDate().toString()));
        reportDto.setTestDate(formatTestDate(r.getTestDate().toString()));
//        reportDto.setClient(r.getClient());
        reportDto.setInstrumentList(r.getInstruments());


        List<DetailResult> dres = new ArrayList<>();
        List<Result> resultList = resultRepository.findResultByReportId(r.getId());
        for (Result res : resultList) {
            Integer resId = res.getId();
            List<DetailResult> detailResultList = detailResultsRepository.findByResult_Id(resId);
            dres.addAll(detailResultList);
        }
        reportDto.setDetailResultList(dres);


        List<DetailResult> detailResultList = detailResultsRepository.findAll();
        Set<Criteria> criterias = new HashSet<>();
        for (
                DetailResult dr : detailResultList) {
            if (dr.getResult().getReport().equals(r)) {
                criterias.add(dr.getCriteria());
            }

        }
        reportDto.setCriteriaList(criterias);

        List<Result> resultList1 = resultRepository.findAll();
        List<Result> results = new ArrayList<>();
        for (
                Result res : resultList1) {
            if (res.getReport().equals(r)) {
                results.add(res);
            }
        }
        reportDto.setResultList(results);


        List<String> personSet = new ArrayList<>();
        for (
                Person p : r.getPersons()) {
            personSet.add(p.getLastName() + " " + p.getName().substring(0, 1) + "."
                    + p.getFirstName().substring(0, 1));
        }
        reportDto.setFioPersons(personSet);

        List<String> personlist = new ArrayList<>();
        for (
                Person p : r.getPersons()) {
            personlist.add(p.getName().substring(0, 1) + "."
                    + p.getFirstName().substring(0, 1) + "." + p.getLastName());
        }
        reportDto.setOifPersons(personlist);

        reportDto.setScheme(r.getScheme());
        return reportDto;
    }


}