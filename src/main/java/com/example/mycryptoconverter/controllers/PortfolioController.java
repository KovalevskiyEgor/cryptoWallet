package com.example.mycryptoconverter.controllers;

import com.example.mycryptoconverter.dto.CryptoDto;
import com.example.mycryptoconverter.mappers.CryptoMapper;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.services.PortfolioService;
import com.example.mycryptoconverter.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}/portfolio")
@RequiredArgsConstructor
@Data
public class PortfolioController {
    private final CryptoMapper cryptoMapper;
    private final UserService userService;
    private final PortfolioService portfolioService;

    @PostMapping("/addCrypto")
    public ResponseEntity<HttpStatus> addCryptoToPortfolio(@RequestBody Portfolio portfolio, @PathVariable Long id){
        portfolioService.addCryptoToPortfolio(id,(portfolio));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping()
    public List<Portfolio> getPortfolio(@PathVariable Long id){
        return portfolioService.getAllByUsersId(id);
    }


}
