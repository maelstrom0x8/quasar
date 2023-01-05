package com.aeflheim.quasar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeflheim.quasar.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
