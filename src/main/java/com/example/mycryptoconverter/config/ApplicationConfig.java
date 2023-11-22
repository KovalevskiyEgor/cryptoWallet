package com.example.mycryptoconverter.config;

import com.example.mycryptoconverter.mappers.CryptoMapper;
import com.example.mycryptoconverter.mappers.UserMapper;
import com.example.mycryptoconverter.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CryptoMapper cryptoMapper(){
        return new CryptoMapper(modelMapper());
    }
    @Bean
    public UserMapper userMapper(){
        return new UserMapper(modelMapper());
    }


}
