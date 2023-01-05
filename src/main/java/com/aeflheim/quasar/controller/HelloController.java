package com.aeflheim.quasar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye :(";
    }

    @GetMapping("/sayonara")
    public String sayonara() {
        return "Sayonara! :)";
    }
    
    @PostMapping("/n/{message}")
    public String message(@PathVariable(name = "message") String message) {
        return message.toUpperCase() + "!";
    }

    @PostMapping("/n/{m1}/{m2}")
    public String message2(@PathVariable(name = "m1") String m1, @PathVariable("m2") String m2) {
        return m1 + " and " + m2 + "!";
    }
}
