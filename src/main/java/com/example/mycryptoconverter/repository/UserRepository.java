package com.example.mycryptoconverter.repository;

import com.example.mycryptoconverter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String recieverUsername);
}
