package com.example.mycryptoconverter.controllers;

import com.example.mycryptoconverter.dto.CryptoDto;
import com.example.mycryptoconverter.mappers.CryptoMapper;
import com.example.mycryptoconverter.services.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {
    private final CryptoService cryptoService;
    private final CryptoMapper cryptoMapper;

    @Operation(summary = "Получаем инфу об определенной крипте")
    @GetMapping("/{crypto}")
    public CryptoDto getCrypto(@PathVariable String crypto){
        return cryptoMapper.toDto(cryptoService.getCryptoByName(crypto));
    }

    @Operation(summary = "Получаем инфу о всей крипте")
    @GetMapping ()
    public List<CryptoDto> getAll(){
        return cryptoService.getAll().stream().map(cryptoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/updateCrypto")
    public ResponseEntity<HttpStatus> update(){
        cryptoService.updateAllCrypto();
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
