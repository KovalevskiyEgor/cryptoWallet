package com.example.mycryptoconverter.services;

import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.repository.CryptoRepository;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class PortfolioService {
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
    private final PortfolioRepository portfolioRepository;
    private final CryptoService cryptoService;

    public ResponseEntity<HttpStatus> addCryptoToPortfolio(Long id, Portfolio portfolio){
        User user = userRepository.findById(id).orElseThrow(); // TODO
        Crypto crypto = cryptoRepository.findByName(portfolio.getCrypto().getName());//TODO

        Optional<Portfolio> portfolioOptional=portfolioRepository.findByUserIdAndCryptoId(user.getId(), crypto.getId());//проверяем есть ли уже такая крипта у этого человека

        if(portfolioOptional.isPresent()){
            Portfolio portfolio1 = portfolioOptional.get();
            //если есть то добавляем ему количество
            portfolio.setAmount(portfolio1.getAmount()+portfolio.getAmount());
            portfolio.setTotalPrice(portfolio.getAmount()*crypto.getPrice());

            portfolioRepository.save(portfolio);

            return ResponseEntity.ok(HttpStatus.OK);
        };

        portfolio.setUser(user);
        portfolio.setCrypto(crypto);
        portfolio.setTotalPrice(portfolio.getAmount()*crypto.getPrice());

        portfolioRepository.save(portfolio);

        return ResponseEntity.ok(HttpStatus.OK);
    };

    public ResponseEntity<HttpStatus> updateTotalPrice(){
        List<Portfolio> list= portfolioRepository.findAll();

        for(Portfolio portfolio:list){
            Crypto crypto1 = cryptoRepository.findByName(portfolio.getCrypto().getName());
            portfolio.setTotalPrice(portfolio.getAmount()*crypto1.getPrice());

            portfolioRepository.save(portfolio);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public List<Portfolio> getAllByUsersId(Long id){
        return portfolioRepository.findAllByUserId(id);
    }


}
