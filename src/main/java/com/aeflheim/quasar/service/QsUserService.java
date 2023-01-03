package com.aeflheim.quasar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aeflheim.quasar.dto.UserDto;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;


public class QsUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public String createUser(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                userDto.getFirstName(), userDto.getLastName());

        User saved = userRepository.save(user);
        return saved.getUsername();
    }
}