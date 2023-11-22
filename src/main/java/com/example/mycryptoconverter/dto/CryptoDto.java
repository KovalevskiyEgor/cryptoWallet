package com.example.mycryptoconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

@Data
@RequiredArgsConstructor

public class CryptoDto {
    @Schema(description = "время")
    public Integer timestamp;

    @Schema(description = "Валюта")
    public String target;

    @Schema(description = "Стоимость")
    private LinkedHashMap<String, Object> rates;

    private String name;
    private Double price;

    private double amount;

}
