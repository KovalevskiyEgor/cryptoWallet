package com.example.mycryptoconverter.controllers;

import com.example.mycryptoconverter.dto.CryptoDto;
import com.example.mycryptoconverter.dto.UserDto;
import com.example.mycryptoconverter.exeptions.AppError;
import com.example.mycryptoconverter.mappers.CryptoMapper;
import com.example.mycryptoconverter.mappers.UserMapper;
import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.services.UserService;
import com.example.mycryptoconverter.utils.BindingResultChecker;
import com.example.mycryptoconverter.validators.UserRegistrationValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRegistrationValidator userValidator;
    private final BindingResultChecker bindingResultChecker;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public void getHello(){
        System.out.println("hello");
    }


    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody @Valid UserDto userDto,
                                       BindingResult bindingResult){
        userValidator.validate(userDto,bindingResult);
        ResponseEntity<?> response = bindingResultChecker.checkBindingResult(bindingResult);

        if(response!=null){
            System.out.println("ss");
            return response;
        }

        return ResponseEntity.ok().body(userService.save(userMapper.fromDto(userDto)));
    }

    @GetMapping("/{id}/portfolio")
    public List<Portfolio> getPortfolio(@PathVariable Long id){
        return userService.getAllPortfolio(id);
    }
}