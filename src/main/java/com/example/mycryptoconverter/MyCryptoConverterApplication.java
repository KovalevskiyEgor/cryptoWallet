package com.example.mycryptoconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@Configuration
public class MyCryptoConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCryptoConverterApplication.class, args);

    }


}
