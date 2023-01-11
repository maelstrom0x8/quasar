package com.aeflheim.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aeflheim.quasar.model.Otp;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.service.QsUserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private QsUserService qsUserService;

    @PostMapping("/user/add")
    public void createUser(@RequestBody User user) {
        qsUserService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user) {
        qsUserService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) {
        if (qsUserService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
