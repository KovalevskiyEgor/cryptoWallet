package com.example.mycryptoconverter.exeptions;

public class UserNotFoundExeption extends RuntimeException{
    public UserNotFoundExeption(String s){
        super(s);
    }
}
