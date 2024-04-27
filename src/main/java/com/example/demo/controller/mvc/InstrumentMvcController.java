package com.example.demo.controller.mvc;

import com.example.demo.dto.InstrumentDto;
import com.example.demo.model.Instrument;
import com.example.demo.service.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
//@EnableWebMvc

@RequestMapping("/instrument")

public class InstrumentMvcController {
    Logger logger = LoggerFactory.getLogger(InstrumentMvcController.class);


    @Autowired
    private InstrumentService instrumentService;


    @GetMapping("/all")
    public String getAllInstrument(Model model) {
        logger.info("get all instruments");

        List<Instrument> instruments = instrumentService.getAllInstruments();
        model.addAttribute("instruments", instruments);
        InstrumentDto instrumentDto = new InstrumentDto();
//        List<String> filtr = instrumentDto.getFiltr();
//        model.addAttribute("filtr", filtr);
        return "list_instruments";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        logger.info("show form for new instrument");

        logger.info("showForm started");
        Instrument instrument = new Instrument();
        model.addAttribute("instrument", instrument);
        return "new_instrument";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("instrument") Instrument instrument, RedirectAttributes redirectAttributes) {
        logger.info("Adding instrument {}", instrument);

        String msg = instrumentService.addInstrument(instrument);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/instrument/all";
    }

    @GetMapping("/edit/{id}")
    public String editInstrument(@PathVariable(name = "id") Integer instrumentId, Model model) {
        Instrument instrument = instrumentService.findInstrumentById(instrumentId);
        model.addAttribute("instrument", instrument);
        logger.info("Show form for edit instrument {}", instrument);
        return "edit_instrument";
    }

    @PostMapping("/edit")
    public String updateInstrument(@ModelAttribute("instrument") Instrument instrument, RedirectAttributes redirectAttributes,
                                   Model model) {
        String msg = instrumentService.updateInstrument(instrument.getId(), instrument);
        redirectAttributes.addFlashAttribute("msg", msg);
        List<Instrument> instruments = instrumentService.getAllInstruments();
        model.addAttribute("instruments", instruments);
        logger.info("Update instrument {}", instrument);

        return "redirect:/instrument/all";
    }


    @GetMapping("/delete/{id}")
    public String deleteInstrument(@PathVariable(name = "id") Integer instrumentId, RedirectAttributes redirectAttributes) {

        String msg = "Невозможно удалить средство измерения";
        try {
            msg = instrumentService.deleteInstrument(instrumentId);
            logger.info("Delete instrument id={}", instrumentId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/instrument/all";
        }

    }


    @GetMapping("/search_list")
    public String listSearchInstruments(Model model, @RequestParam String start) {
        Set<Instrument> instruments = instrumentService.findBy(start);
        model.addAttribute("instruments", instruments);

        return "list_instruments";
    }
}







