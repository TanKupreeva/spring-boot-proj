package com.example.demo.controller.mvc;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Client;
import com.example.demo.model.Grs;
import com.example.demo.model.Umg;
import com.example.demo.service.ClientService;
import com.example.demo.service.GrsService;
import com.example.demo.service.OfficeService;
import com.example.demo.service.UmgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/grs")
public class GrsMvcController {

    Logger logger = LoggerFactory.getLogger(GrsMvcController.class);


    @Autowired
    GrsService grsService;

    @Autowired
    UmgService umgService;

    @Autowired
    OfficeService officeService;

    @Autowired
    ClientService clientService;

    @GetMapping("/all")
    public String getAllGrses(Model model) {
        logger.info("get all grses");

        List<Grs> grses = grsService.getAllGrses();
        model.addAttribute("grses", grses);
        return "list_grses";
    }

    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new grs");

        Grs grs = new Grs();
        List<Umg> umges = umgService.getAllUmges();

        model.addAttribute("grs", grs);
        model.addAttribute("clients",clientService.getAllClients());
        return "new_grs";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("grs") Grs grs, RedirectAttributes redirectAttributes) {
        logger.info("Adding grs {}", grs);

        String msg = grsService.addGrs(grs);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/grs/all";
    }


    @GetMapping("/edit/{id}")
    public String editGrs(@PathVariable(name = "id") Integer id, Model model) {
        Grs grs = grsService.findGrsById(id);
        model.addAttribute("grs", grs);
       List <Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        logger.info("Show form for edit grs {}", grs);
        return "edit_grs";
    }

    @PostMapping("/edit")
    public String updateGrs(@ModelAttribute("grs") Grs grs, Model model,
                            RedirectAttributes redirectAttributes) {
        String msg = grsService.updateGrs(grs.getId(), grs);
        redirectAttributes.addFlashAttribute("msg", msg);
        List<Grs> grses = grsService.getAllGrses();
        model.addAttribute("grses", grses);
        logger.info("Update grs {}", grs);

        return "redirect:/grs/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrs(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        String msg = "Невозможно удалить объект";
        try {
            msg = grsService.deleteGrs(id);
            logger.info("Delete grs id={}", id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/grs/all";
        }

    }


    @GetMapping("/search_list")
    public String listsearchGrses(Model model, @RequestParam String start) {
        Set<Grs> grses = grsService.findBy(start);
        model.addAttribute("grses", grses);

        return "list_grses";
    }
}
