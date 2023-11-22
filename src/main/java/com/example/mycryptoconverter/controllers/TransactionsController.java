package com.example.mycryptoconverter.controllers;

import com.example.mycryptoconverter.mappers.CryptoMapper;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.services.CryptoService;
import com.example.mycryptoconverter.services.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionsController {
    private final CryptoService cryptoService;
    private final TransactionsService transactionsService;
    private final CryptoMapper cryptoMapper;

    @PostMapping("/send/{senderId}")
    public ResponseEntity<?> sendCrypto(@PathVariable Long senderId, @RequestBody @Valid Portfolio portfolio,
                                        BindingResult bindingResult){
        return transactionsService.sendCrypto(senderId,portfolio,bindingResult);

    }

}
