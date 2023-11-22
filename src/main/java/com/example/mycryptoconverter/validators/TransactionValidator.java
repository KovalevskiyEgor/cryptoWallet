package com.example.mycryptoconverter.validators;

import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.services.PortfolioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@RequiredArgsConstructor
@Data
@Component
public class TransactionValidator {
    private final PortfolioService portfolioService;
    private final PortfolioRepository portfolioRepository;


    public boolean supports(Class<?> clazz) {
        return Portfolio.class.equals(clazz);
    }

    public void validate(Long senderId, Object target, Errors errors){
        Portfolio portfolioToSend = (Portfolio) target;

        if(!supports(portfolioToSend.getClass())) return;

        Crypto cryptoToSend = portfolioToSend.getCrypto();
        User user = portfolioToSend.getUser();


        Optional<Portfolio> portfolioSenderHaveOptional = portfolioRepository.findByUserIdAndCryptoName(senderId, cryptoToSend.getName());

        if(!portfolioSenderHaveOptional.isPresent()) {
            errors.rejectValue("crypto","400","you dont have this crypto");
            return;
        }

        Portfolio portfolioSenderHave=portfolioSenderHaveOptional.get();

        if(portfolioToSend.getAmount()>portfolioSenderHave.getAmount()){
            errors.rejectValue("crypto","400","you dont have enough amount of this crypto");

        }
    }

}
