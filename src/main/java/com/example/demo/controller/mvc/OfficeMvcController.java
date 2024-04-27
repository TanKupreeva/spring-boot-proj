package com.example.demo.controller.mvc;

import com.example.demo.model.Instrument;
import com.example.demo.model.Office;
import com.example.demo.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/office")
//@EnableWebMvc
public class OfficeMvcController {

    Logger logger = LoggerFactory.getLogger(OfficeMvcController.class);


    @Autowired
    private OfficeService officeService;

    @GetMapping("/all")
    public String getAllOffices(Model model) {
        logger.info("get all offices");

        List<Office> offices = officeService.getAllOffices();
        model.addAttribute("offices", offices);
        return "list_offices";
    }

    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new office");

        Office office = new Office();
        model.addAttribute("office", office);
        return "new_office";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("office") Office office, RedirectAttributes redirectAttributes) {
        logger.info("Adding office {}", office);

        String msg = officeService.addOffice(office);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/office/all";
    }

    @GetMapping("/edit/{id}")
    public String editOffice(@PathVariable(name = "id") Integer id, Model model) {
        Office office = officeService.findOfficeById(id);
        model.addAttribute("office", office);
        logger.info("Show form for edit office {}", office);
        return "edit_office";
    }

    @PostMapping("/edit")
    public String updateOffice(@ModelAttribute("office") Office office,

                                   Model model, RedirectAttributes redirectAttributes) {
        String msg = officeService.updateOffice(office.getId(), office);
        redirectAttributes.addFlashAttribute("msg", msg);
        List<Office> offices = officeService.getAllOffices();
        model.addAttribute("offices", offices);
        logger.info("Update office {}", office);

        return "redirect:/office/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffice(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = "Невозможно удалить службу";
        try {
            msg = officeService.deleteOffice(id);
            logger.info("Delete office id={}", id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/office/all";
        }

    }


}
