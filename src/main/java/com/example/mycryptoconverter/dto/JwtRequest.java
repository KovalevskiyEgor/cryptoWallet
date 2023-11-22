package com.example.mycryptoconverter.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtRequest {
    private String password;
    private String username;
}
