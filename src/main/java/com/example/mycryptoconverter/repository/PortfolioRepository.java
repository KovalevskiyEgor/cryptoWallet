package com.example.mycryptoconverter.repository;

import com.example.mycryptoconverter.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserIdAndCryptoId(Long senderId, Long id);

    List<Portfolio> findAllByUserId(Long id);

    Optional<Portfolio> findByUserIdAndCryptoName(Long id, String name);
}
