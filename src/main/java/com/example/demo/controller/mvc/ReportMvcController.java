package com.example.demo.controller.mvc;

//import com.example.demo.dto.PersonDto;
import com.example.demo.dto.ReportDto;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/report")
public class ReportMvcController {
    Logger logger = LoggerFactory.getLogger(ReportMvcController.class);


    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    ReportService reportService;
    @Autowired
    ClientService clientService;
    @Autowired
    UmgService umgService;
    @Autowired
    GrsService grsService;
    @Autowired
    ConditionService conditionService;
    @Autowired
    InstrumentService instrumentService;
    @Autowired
    PersonService personService;
    @Autowired
    ResultService resultService;
    @Autowired
    CriteriaService criteriaService;
    @Autowired
    OfficeService officeService;
    @Autowired
    DetailResultsService detailResultsService;

    @GetMapping("/all")
    public String getAllReports(Model model) {
        logger.info("get all reports");

        List<ReportDto> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "list_reports";
    }

    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = reportService.deleteReport(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        logger.info("Delete report id={}", id);

        return "redirect:/report/all";
    }


    @GetMapping("/details/{registrationNum}")
    public String showReport(@PathVariable(name = "registrationNum") String registrationNum, Model model) {
        List<ReportDto> reports = reportService.getAllReports();
        ReportDto report = new ReportDto();
        for (ReportDto r : reports) {
            if (r.getRegistrationNum().equals(registrationNum.replace("-", "/"))) {
                report = r;
                break;
            }

        }
        model.addAttribute("report", report);
//        model.addAttribute("client", clientService.findClientById(report.getClient().getId()));
        model.addAttribute("conditions", conditionService.findByReportId(report.getId()));


        List<Instrument> instruments = report.getInstrumentList();
        model.addAttribute("instruments", instruments);


        Set<Criteria> criterias = report.getCriteriaList();
        model.addAttribute("criterias", criterias);

        List<Result> results = resultService.getAllResultsByReport(report.getId());
        model.addAttribute("results", results);
        List<DetailResult> detailResultList = report.getDetailResultList();
        List<Integer> listT = new ArrayList<>();

        for (DetailResult dr : detailResultList) {
            listT.add(dr.getT());

        }
        model.addAttribute("listT", listT);


        List<Person> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);


        return "details_reports";
    }


    @GetMapping("/search_list")
    public String listsearchReports(Model model, @RequestParam String start) {
        Set<ReportDto> reports = reportService.findBy(start);
        model.addAttribute("reports", reports);

        return "list_reports";
    }


    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new report");

        Report report = new Report();
        model.addAttribute("report", report);
        model.addAttribute("umges", umgService.getAllUmges());
        model.addAttribute("offices", officeService.getAllOffices());
        List<Grs> grses = grsService.getAllGrses();
        Collections.sort(grses);
        model.addAttribute("grses", grses);

        List<Instrument> instruments = instrumentService.getAllInstruments();
        Collections.sort(instruments);
        model.addAttribute("instruments", instruments);



        List<Person> persons = personService.getAllPersons();
        Collections.sort(persons);
        model.addAttribute("persons", persons);

        model.addAttribute("clients", clientService.getAllClients());

        return "new_report";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("report") Report report, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            report.setScheme(resultFileName);
        }
        String msg = reportService.addReport(report);


        model.addAttribute("msg", msg);
        model.addAttribute("report", report);
        logger.info("Adding report {}", report);

        return "new_report_condition";
//        return "redirect:/report/all";
    }

}
