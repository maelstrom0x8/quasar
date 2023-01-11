package com.aeflheim.quasar;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;


public class DefaulUserLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaulUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
    public void load() {
        User anna = new User();
        anna.setUsername("anna");
        anna.setPassword(passwordEncoder.encode("sandstorm"));
        

        User king = new User();
        king.setUsername("king");
        king.setPassword(passwordEncoder.encode("infinity"));
        

        userRepository.saveAll(List.of(anna, king));
    }
}
