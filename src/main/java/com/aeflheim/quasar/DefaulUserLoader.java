package com.aeflheim.quasar;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DefaulUserLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaulUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void load() {
        User anna = new User();
        anna.setUsername("anna");
        anna.setPassword(passwordEncoder.encode("sandstorm"));
        anna.setAuthority("READ");

        User king = new User();
        king.setUsername("king");
        king.setPassword(passwordEncoder.encode("infinity"));
        king.setAuthority("READ");

        userRepository.saveAll(List.of(anna, king));
    }
}
