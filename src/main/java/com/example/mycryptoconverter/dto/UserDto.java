package com.example.mycryptoconverter.dto;

import com.example.mycryptoconverter.models.Crypto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDto {

    private int id;

    @Size(min = 8,message = "Минимальная длина логина - 7 символов")
    private String username;

    @Size(min = 8,message = "Минимальная длина пароля - 7 символов")
    private String password;

    @Size(min = 8,message = "Минимальная длина пароля - 7 символов")
    private String confirmPassword;

    private List<Crypto> usersCrypto;



}
