package com.aeflheim.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeflheim.quasar.dto.UserDto;
import com.aeflheim.quasar.service.QsUserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private QsUserService qsUserService;

    
    public String createUser(UserDto userDto) {
        return qsUserService.createUser(userDto);
    }



}
