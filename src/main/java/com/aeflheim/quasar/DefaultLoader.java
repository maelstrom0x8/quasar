package com.aeflheim.quasar;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aeflheim.quasar.model.Client;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.ClientRepository;
import com.aeflheim.quasar.repository.UserRepository;
import com.aeflheim.quasar.service.QsClientService;

import jakarta.annotation.PostConstruct;

@Component
public class DefaultLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final QsClientService clientService;


    public DefaultLoader(UserRepository userRepository, PasswordEncoder passwordEncoder,
            QsClientService clientService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
    }


    @PostConstruct
    public void load() {
        User anna = new User();
        anna.setUsername("anna");
        anna.setPassword(passwordEncoder.encode("sandstorm"));
        anna.setAuthority("read");

        User king = new User();
        king.setUsername("king");
        king.setPassword(passwordEncoder.encode("infinity"));
        king.setAuthority("openid");

        userRepository.saveAll(List.of(anna, king));

        Client client = new Client();
        client.setClientId("client");
        client.setClientSecret("secret");
        client.setRedirectUri("https://springone.io/authorized");
        client.setScope("openid");
        client.setAuthenticationMethod("client_secret_basic");
        client.setGrantType("authorization_code");
        
        clientService.save(Client.from(client));

    }
}
