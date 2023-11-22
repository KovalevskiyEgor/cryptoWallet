package com.example.mycryptoconverter.repository;

import com.example.mycryptoconverter.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleUser);
}
