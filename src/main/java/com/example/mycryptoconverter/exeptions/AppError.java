package com.example.mycryptoconverter.exeptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AppError{
    private int status;
    private String message;
    private Date timestamp;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
