package com.example.demo.controller.mvc;

import com.example.demo.dto.CriteriaDto;
import com.example.demo.model.Criteria;
import com.example.demo.model.Grs;
import com.example.demo.model.Mark;
import com.example.demo.model.Umg;
import com.example.demo.service.CriteriaService;
import com.example.demo.service.MarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/criteria")
public class CriteriaMvcController {
    Logger logger = LoggerFactory.getLogger(CriteriaMvcController.class);

    @Autowired
    CriteriaService criteriaService;
    @Autowired
    MarkService markService;

    @GetMapping("/all")
    public String getAllCriterias(Model model) {
        List<Criteria> criterias = criteriaService.getAllCriterias();
        model.addAttribute("criterias", criterias);
        logger.info("get all criterias");

        return "list_criterias";
    }


    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new criteria");

        Criteria criteria = new Criteria();
        List<Mark> marks = new ArrayList<>();
        CriteriaDto criteriaDto = new CriteriaDto();
        List<String> list = criteriaDto.getList();
        model.addAttribute("criteria", criteria);
        model.addAttribute("marks", marks);
        model.addAttribute("list", list);
        return "new_criteria";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("criteria") Criteria criteria, RedirectAttributes redirectAttributes) {
        logger.info("Adding criteria {}", criteria);

        markService.addMark(criteria.getExcellent());
        markService.addMark(criteria.getGood());
        markService.addMark(criteria.getAcceptable());
        markService.addMark(criteria.getRequiresAction());
        markService.addMark(criteria.getUnacceptable());
        String msg = criteriaService.addCriteria(criteria);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/criteria/all";
    }



    @GetMapping("/edit/{id}")
    public String editCriteria(@PathVariable(name = "id") Integer id, Model model) {
        Criteria criteria = criteriaService.findCriteriaById(id);

        Mark good = criteria.getGood();
        Mark excellent = criteria.getExcellent();
        Mark acceptable = criteria.getAcceptable();
        Mark requiredAction = criteria.getRequiresAction();
        Mark unacceptable = criteria.getUnacceptable();
        model.addAttribute("criteria", criteria);
        model.addAttribute("good",good);
        model.addAttribute("excellent",excellent);
        model.addAttribute("acceptable",acceptable);
        model.addAttribute("requiredAction",requiredAction);
        model.addAttribute("unacceptable",unacceptable);
        logger.info("Show form for edit criteria {}", criteria);

        return "edit_criteria";
    }

    @PostMapping("/edit")
    public String updateGrs(@ModelAttribute("criteria") Criteria criteria, Model model,
                            RedirectAttributes redirectAttributes) {
        markService.updateMark(criteria.getExcellent());
        markService.updateMark(criteria.getGood());
        markService.updateMark(criteria.getAcceptable());
        markService.updateMark(criteria.getRequiresAction());
        markService.updateMark(criteria.getUnacceptable());
        String msg = criteriaService.updateCriteria(criteria);
        redirectAttributes.addFlashAttribute("msg", msg);

        List<Criteria> criterias = criteriaService.getAllCriterias();
        model.addAttribute("criterias", criterias);
        logger.info("Update criteria {}", criteria);

        return "redirect:/criteria/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCritteria(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = "Невозможно удалить критерию";

        try {
        Criteria criteria = criteriaService.findCriteriaById(id);
       msg = criteriaService.deleteCriteria(id);

        markService.deleteMark(criteria.getExcellent().getId());
        markService.deleteMark(criteria.getGood().getId());
        markService.deleteMark(criteria.getAcceptable().getId());
        markService.deleteMark(criteria.getRequiresAction().getId());
        markService.deleteMark(criteria.getUnacceptable().getId());
            logger.info("Delete criteria id={}", id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);


        return "redirect:/criteria/all";



        }



    }

}
