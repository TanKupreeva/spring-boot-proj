package com.example.demo.controller.mvc;

import com.example.demo.model.Client;
import com.example.demo.model.Office;
import com.example.demo.model.Umg;
import com.example.demo.service.ClientService;
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
@RequestMapping("/client")
public class ClientMvcController {


    Logger logger = LoggerFactory.getLogger(ClientMvcController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private UmgService umgService;

    @GetMapping("/all")
    public String getAllClients(Model model) {
        logger.info("get all clients");

        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "list_clients";
    }

    @GetMapping("/new")
    public String getShowForm(Model model) {
        logger.info("show form for new client");

        Client client = new Client();
        List<Office> offices = officeService.getAllOffices();
        List<Umg> umges = umgService.getAllUmges();
        model.addAttribute("umges", umges);
        model.addAttribute("client", client);
        model.addAttribute("offices", offices);
        return "new_client";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("client") Client client, RedirectAttributes redirectAttributes) {
        logger.info("Adding client {}", client);

        String msg = clientService.addClient(client);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/client/all";
    }


    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable(name = "id") Integer id, Model model) {

        Client client = clientService.findClientById(id);
        model.addAttribute("client", client);
        List<Office> offices = officeService.getAllOffices();
        model.addAttribute("offices", offices);
        List<Umg> umges = umgService.getAllUmges();
        model.addAttribute("umges", umges);

        logger.info("Show form for edit client {}", client);
        return "edit_client";
    }

    @PostMapping("/edit")
    public String updateClient(@ModelAttribute("client") Client client, Model model,
                               RedirectAttributes redirectAttributes) {
        String msg = clientService.updateClient(client.getId(), client);
        redirectAttributes.addFlashAttribute("msg", msg);
        logger.info("Update client {}", client);

        return "redirect:/client/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {

        String msg = "Невозможно удалить заказчика";
        try {
            msg = clientService.deleteClient(id);
            logger.info("Delete client id={}", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redirectAttributes.addFlashAttribute("msg", msg);


            return "redirect:/client/all";
        }

    }


}
