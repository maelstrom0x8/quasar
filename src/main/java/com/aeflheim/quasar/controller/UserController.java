package com.aeflheim.quasar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeflheim.quasar.dto.UserDto;
import com.aeflheim.quasar.service.QsUserDetailsService;

@RestController
@RequestMapping("/auth")
public class UserController {

    private QsUserDetailsService userDetailsService;

    public UserController(QsUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public String createUser(UserDto userDto) {
        return userDetailsService.createUser(userDto);
    }

}
