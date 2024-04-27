package com.example.demo.controller.mvc;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Condition;
import com.example.demo.model.Grs;
import com.example.demo.model.Report;
import com.example.demo.model.Umg;
import com.example.demo.service.ConditionService;
import com.example.demo.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/condition")
public class ConditionMvcController {

    Logger logger = LoggerFactory.getLogger(ConditionMvcController.class);

    @Autowired
    ConditionService conditionService;
    @Autowired
    ReportService reportService;

    @GetMapping("/new/{id}")

    public String getShowForm(@PathVariable(name = "id") Integer idReport, Model model) {
        logger.info("show form for new condition");

        Condition condition = new Condition();
        model.addAttribute("condition", condition);
        model.addAttribute("report",reportService.findById(idReport));
        return "new_condition";
    }

    @PostMapping("/new/{id}")
    public String submitForm(@ModelAttribute("condition") Condition condition,@PathVariable(name = "id") Integer idReport,Model model) {
        logger.info("Adding condition {}", condition);

        condition.setReport(reportService.findById(idReport));
        String msg = conditionService.addCondition(condition);
        model.addAttribute("msg", msg);
        model.addAttribute("report", reportService.findById(idReport));
        return "new_report_condition";
    }

    @GetMapping("/all")
    public String getAllConditions(Model model) {
        logger.info("get all conditions");

        List<Condition> conditions = conditionService.getAllConditions();
        model.addAttribute("conditions", conditions);
        return "list_conditions";
    }



    @GetMapping("/edit/{id}")
    public String editCondition(@PathVariable(name = "id") Integer id, Model model) {
        Condition condition = conditionService.findById(id);
        model.addAttribute("condition", condition);
        List <ReportDto> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        logger.info("Show form for edit condition {}", condition);
        return "edit_condition";
    }

    @PostMapping("/edit")
    public String updateGrs(@ModelAttribute("condition") Condition condition, Model model,
                            RedirectAttributes redirectAttributes) {
        String msg = conditionService.updateCondition(condition.getId(), condition);
        redirectAttributes.addFlashAttribute("msg", msg);
        logger.info("Update condition {}", condition);

        return "redirect:/condition/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrs(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = conditionService.deleteCondition(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        logger.info("Delete condition id={}", id);
        return "redirect:/condition/all";
    }

}
