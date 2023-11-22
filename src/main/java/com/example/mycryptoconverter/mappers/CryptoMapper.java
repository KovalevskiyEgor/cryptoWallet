package com.example.mycryptoconverter.mappers;

import com.example.mycryptoconverter.dto.CryptoDto;
import com.example.mycryptoconverter.models.Crypto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
@Data
public class CryptoMapper {
    private final ModelMapper modelMapper;

    public Crypto fromDto(CryptoDto cryptoDto){
        return modelMapper.map(cryptoDto,Crypto.class);
    }
    public CryptoDto toDto(Crypto crypto){
        return modelMapper.map(crypto, CryptoDto.class);
    }

}

