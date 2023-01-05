package com.aeflheim.quasar.service;

import java.util.Optional;

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

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    public QsUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                userDto.getFirstName(), userDto.getLastName());

        User saved = userRepository.save(user);
        return saved.getUsername();
    }

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return new QsUserDetails(user.get());
        }
        throw new EmailNotFoundException("Error occured in authentication");
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
