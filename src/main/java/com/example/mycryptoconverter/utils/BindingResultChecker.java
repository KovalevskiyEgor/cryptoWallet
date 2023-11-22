package com.example.mycryptoconverter.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class BindingResultChecker {
    public ResponseEntity<?> checkBindingResult(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();

        if(bindingResult.hasErrors()){
            for(FieldError error: bindingResult.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return null;
    }
}
