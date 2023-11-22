package com.example.mycryptoconverter.repository;

import com.example.mycryptoconverter.models.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CryptoRepository extends JpaRepository<Crypto, Integer> {

    Crypto findByName(String name);

    //void updateAllBy(@Param(value = "name") String name, @Param(value = "price") Double price);
}
