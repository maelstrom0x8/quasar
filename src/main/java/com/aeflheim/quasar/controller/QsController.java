package com.aeflheim.quasar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QsController {

    @GetMapping("")
    public String index() {
        return "index";
    }
    
}
