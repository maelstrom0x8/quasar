package com.aeflheim.quasar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeflheim.quasar.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Optional<Client> findByClientId(String clientId);
}
