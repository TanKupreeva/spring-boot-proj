package com.example.demo.controller.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class ContentController {
    Logger logger = LoggerFactory.getLogger(ContentController.class);


    @GetMapping
    public String content(){
        return "navigation";
    }
}
