package com.aeflheim.quasar.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.service.InMemoryUserDetailsService;

@Configuration
public class UserManagement {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = new User("king", "infinity", "READ");
        UserDetails userDetails1 = new User("anna", "sandstorm", "READ");
        List<UserDetails> users = List.of(userDetails, userDetails1);

        return new InMemoryUserDetailsService(users);

    }
}
