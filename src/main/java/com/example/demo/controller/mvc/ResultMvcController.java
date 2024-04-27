package com.example.demo.controller.mvc;

import com.example.demo.model.*;
import com.example.demo.service.CriteriaService;
import com.example.demo.service.ReportService;
import com.example.demo.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/result")
public class ResultMvcController {

    Logger logger = LoggerFactory.getLogger(ResultMvcController.class);

    @Autowired
    ResultService resultService;
    @Autowired
    ReportService reportService;
    @Autowired
    CriteriaService criteriaService;


    @GetMapping("/new/{id}")
    public String getShowForm(Model model, @PathVariable(name = "id") Integer idReport) {
        logger.info("show form for new result");

        Result result = new Result();
        model.addAttribute("result", result);
        model.addAttribute("criterias", criteriaService.getAllCriterias());
        model.addAttribute("report",reportService.findById(idReport));
        return "new_result";
    }

    @PostMapping("/new/{id}")
    public String submitForm(@ModelAttribute("result") Result result,@PathVariable(name = "id") Integer idReport, Model model) {
        result.setReport(reportService.findById(idReport));

        String msg = resultService.addResult(result);
       model.addAttribute("msg", msg);
        model.addAttribute("report", reportService.findById(idReport));
        logger.info("Adding result {}", result);

        return "new_report_condition";
    }




    @GetMapping("/edit/{id}")
    public String editResult(@PathVariable(name = "id") Integer id, Model model) {
        Result result = resultService.findResultById(id);
        model.addAttribute("result", result);
        logger.info("Show form for edit result {}", result);
//        int reportId = result.getReport().getId();
//        Report report  = reportService.findById(21);
//        model.addAttribute("report",report);
        return "edit_result";
    }

    @PostMapping("/edit/{id}")
    public String updateResult(@ModelAttribute("result") Result result,
                               Model model, RedirectAttributes redirectAttributes) {
        int reportId = result.getReport().getId();

        result.setReport(reportService.findById(reportId));

        String msg = resultService.updateResult(result.getId(), result);
        redirectAttributes.addFlashAttribute("msg", msg);
        List<Result> results = resultService.getAllResultsByReport(reportId);
        model.addAttribute("results", results);
        model.addAttribute("report", reportService.findById(reportId));
        logger.info("Update result {}", result);

        return "list_results";
    }

    @GetMapping("/all/{id}")
    public String getAll(Model model, @PathVariable(name = "id") Integer idReport) {
        logger.info("get all results");

        List<Result> results = resultService.getAllResultsByReport(idReport);
        model.addAttribute("results", results);
        model.addAttribute("report",reportService.findById(idReport));
        return "list_results";
    }


    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes,Model model) {
        Result result = resultService.findResultById(id);
        int reportId = result.getReport().getId();
        model.addAttribute("report",reportService.findById(reportId));
        String msg = resultService.deleteResult(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        model.addAttribute("results", resultService.getAllResultsByReport(reportId));
        logger.info("Delete result id={}", id);

        return "list_results";
    }


}
