package com.example.mycryptoconverter.validators;

import com.example.mycryptoconverter.dto.UserDto;
import com.example.mycryptoconverter.exeptions.UserNotFoundExeption;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class UserRegistrationValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("1");
        UserDto userDto = (UserDto) target;
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            errors.rejectValue("user","401","пароли не совпадают");
            return;
        }
        User user;
        String s= "Buzz";
        char[] arr = s.toCharArray();
        s.getBytes();

        try {
            user = userService.findByUsername(userDto.getUsername());
            List<?> list = new LinkedList<>();
        }
        catch (UserNotFoundExeption exeption){
            return;
        }
        if(user!=null) errors.rejectValue("user","401","пользовательн с таким именем уже существует");

//jdbc:postgresql://localhost:15432/myCryptoDb
    }
}
