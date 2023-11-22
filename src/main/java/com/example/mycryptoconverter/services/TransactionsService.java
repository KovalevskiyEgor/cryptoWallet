package com.example.mycryptoconverter.services;

import com.example.mycryptoconverter.exeptions.UserNotFoundExeption;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.repository.UserRepository;
import com.example.mycryptoconverter.validators.TransactionValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class TransactionsService {
    private final CryptoService cryptoService;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final PortfolioService portfolioService;
    private final TransactionValidator transactionValidator;

    public ResponseEntity<?> sendCrypto(Long senderId, Portfolio portfolio, BindingResult bindingResult){
        transactionValidator.validate(senderId,portfolio,bindingResult); // смотрим есть ли нужна крипта и ее количество у отправителя

        Map<String, String> errors = new HashMap<>();

        if(bindingResult.hasErrors()){
            for(FieldError error: bindingResult.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        User reciever = userRepository.findByUsername(portfolio.getUser().getUsername()).orElseThrow(()
            ->new UserNotFoundExeption("User u trying to send ur crypto not found"));

        portfolioService.addCryptoToPortfolio(reciever.getId(), portfolio); //добавляем крипту получателю

        Portfolio senderPortfolio= portfolioRepository.findByUserIdAndCryptoName(senderId,
                        portfolio.getCrypto().getName())
                        .orElseThrow(); //достаем крипту у чела который отправляет чтобы уменьшить количество

        if(senderPortfolio.getAmount()-portfolio.getAmount()==0) {
            portfolioRepository.delete(senderPortfolio); // если у отправителя не осталось этой крипты, то удаляем эту крипту из его рюкзака
        }
        else {
            senderPortfolio.setAmount(senderPortfolio.getAmount()-portfolio.getAmount());//уменьшаем количество крипты на ту что отправил
            senderPortfolio.setId(senderPortfolio.getId());
            portfolioRepository.save(senderPortfolio);

            portfolioService.updateTotalPrice();// обновляем количество денег которое лежит у него в этой крипте исходя из нового количества
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
