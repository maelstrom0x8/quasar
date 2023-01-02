package com.aeflheim.quasar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aeflheim.quasar.config.QsUserDetails;
import com.aeflheim.quasar.dto.UserDto;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;

@Service
public class QsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String createUser(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                userDto.getFirstName(), userDto.getLastName());

        User saved = userRepository.save(user);
        return saved.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return new QsUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Error occured in authentication");
    }

}
