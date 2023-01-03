package com.aeflheim.quasar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aeflheim.quasar.dto.UserDto;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;
import com.aeflheim.quasar.service.QsUserDetailsService;

import jakarta.annotation.PostConstruct;

@Component
public class DefaulUserLoader {

    @Autowired
    private QsUserDetailsService qsUserDetailsService;

    @PostConstruct
    public void load() {
        UserDto anna = new UserDto();
        anna.setUsername("anna");
        anna.setPassword("sandstorm");

        UserDto king = new UserDto();
        king.setUsername("king");
        king.setPassword("infinity");

        qsUserDetailsService.createUser(anna);
        qsUserDetailsService.createUser(king);

    }
}
