package com.example.demo.controller.mvc;

import com.example.demo.model.Office;
import com.example.demo.model.Umg;
import com.example.demo.service.OfficeService;
import com.example.demo.service.UmgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/umg")
public class UmgMvcController {

    Logger logger = LoggerFactory.getLogger(UmgMvcController.class);

    @Autowired
    private UmgService umgService;

    @GetMapping("/all")
    public String getAllUmges(Model model) {
        logger.info("get all umges");

        List<Umg> umges = umgService.getAllUmges();
        model.addAttribute("umges", umges);
        return "list_umges";
    }

    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new umg");

        Umg umg = new Umg();
        model.addAttribute("umg", umg);
        return "new_umg";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("umg") Umg umg, RedirectAttributes redirectAttributes) {
        logger.info("Adding umg {}", umg);

        String msg = umgService.addUmg(umg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/umg/all";
    }

    @GetMapping("/edit/{id}")
    public String editUmg(@PathVariable(name = "id") Integer id, Model model) {
        Umg umg = umgService.findUmgById(id);
        model.addAttribute("umg", umg);
        logger.info("Show form for edit umg {}", umg);
        return "edit_umg";
    }

    @PostMapping("/edit")
    public String updateUmg(@ModelAttribute("umg") Umg umg, Model model,
                            RedirectAttributes redirectAttributes) {
        String msg = umgService.updateUmg(umg.getId(), umg);
        redirectAttributes.addFlashAttribute("msg", msg);
        List<Umg> umges = umgService.getAllUmges();
        model.addAttribute("umges", umges);
        logger.info("Update umg {}", umg);

        return "redirect:/umg/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUmg(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {


        String msg = "Невозможно удалить УМГ";
        try {
            msg = umgService.deleteUmg(id);
            logger.info("Delete umg id={}", id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);


            return "redirect:/umg/all";
        }

    }

}

