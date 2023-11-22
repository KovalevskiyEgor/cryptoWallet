package com.example.mycryptoconverter.mappers;

import com.example.mycryptoconverter.dto.CryptoDto;
import com.example.mycryptoconverter.dto.UserDto;
import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
@Data
public class UserMapper {
    private final ModelMapper modelMapper;

    public User fromDto(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

}

