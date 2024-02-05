package com.example.mycryptoconverter.service;

import com.example.mycryptoconverter.repository.CryptoRepository;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.repository.RoleRepository;
import com.example.mycryptoconverter.repository.UserRepository;
import com.example.mycryptoconverter.services.CryptoService;
import com.example.mycryptoconverter.services.PortfolioService;
import com.example.mycryptoconverter.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CryptoRepository cryptoRepository;
    @Mock
    private PortfolioService portfolioService;
    @Mock
    private CryptoService cryptoService;
    @Mock
    private PortfolioRepository portfolioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void findByUsernameThrowsException(){
        String name = "sdfdsf";
        assertThrows(UsernameNotFoundException.class, ()-> userService.findByUsername(name));
    }

    @Test
    void findByUserIdThrowsException(){
        Long id = 123123213123L;
        assertThrows(UsernameNotFoundException.class, ()-> userService.getUserById(id));
    }




}
