package com.aeflheim.quasar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aeflheim.quasar.config.QsUserDetails;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.UserRepository;

public class QsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return new QsUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Error occured in authentication");
    }

}
