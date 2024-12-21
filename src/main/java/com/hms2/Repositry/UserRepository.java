package com.hms2.Repositry;

import com.hms2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPassword(String password);
}