package com.aeflheim.quasar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeflheim.quasar.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, String> {
    
    Optional<Otp> findByUsername(String username);
}
