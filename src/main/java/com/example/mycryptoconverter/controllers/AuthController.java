package com.example.mycryptoconverter.controllers;

import com.example.mycryptoconverter.dto.JwtRequest;
import com.example.mycryptoconverter.dto.JwtResponse;
import com.example.mycryptoconverter.exeptions.AppError;
import com.example.mycryptoconverter.services.AuthService;
import com.example.mycryptoconverter.services.UserService;
import com.example.mycryptoconverter.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest){
        return authService.createAuthToken(jwtRequest);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

}
