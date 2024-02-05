package com.example.mycryptoconverter.service;

import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.repository.CryptoRepository;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.repository.UserRepository;
import com.example.mycryptoconverter.services.CryptoService;
import com.example.mycryptoconverter.services.PortfolioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CryptoRepository cryptoRepository;
    @Mock
    private PortfolioRepository portfolioRepository;
    @Mock
    private CryptoService cryptoService;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    void addCryptoToPortfolio(){
        Long userId = 15L;

        User mockUser = User.builder().password("12345678").username("misha123").id(userId).build();
        Crypto mockCrypto = Crypto.builder().price(14.49013).target("USD").name("LINK").timestamp(1700141406).build();
        Portfolio portfolio = Portfolio.builder().user(mockUser).crypto(mockCrypto).amount(1).build();

        when(userRepository.findById(15L)).thenReturn(java.util.Optional.of(mockUser));
        when(cryptoRepository.findByName(portfolio.getCrypto().getName())).thenReturn(mockCrypto);
        when(portfolioRepository.findByUserIdAndCryptoId(mockUser.getId(), mockCrypto.getId())).thenReturn(Optional.empty());

        ResponseEntity<HttpStatus> responseEntity = portfolioService.addCryptoToPortfolio(userId, portfolio);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateTotalPrice(){
        ResponseEntity<HttpStatus> status = portfolioService.updateTotalPrice();
        List<Portfolio> list= portfolioRepository.findAll();

        for (Portfolio portfolio : list) {
            Crypto crypto = cryptoRepository.findByName(portfolio.getCrypto().getName());
            double expectedTotalPrice = portfolio.getAmount() * crypto.getPrice();
            assertEquals(expectedTotalPrice, portfolio.getTotalPrice(), 0.001); // Допускаемую погрешность можно уточнить
        }

        assertEquals(status.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getAllUsersById(){
        Long userId = 14L;
        User user = User.builder().username("misha123").password("12345678").id(userId).build();

        Portfolio portfolio1 = Portfolio.builder().id(1).user(user).build();
        Portfolio portfolio2 = Portfolio.builder().id(2).user(user).build();
        List<Portfolio> portfolios = List.of(portfolio1, portfolio2);

        when(portfolioRepository.findAllByUserId(userId)).thenReturn(portfolios);

        List<Portfolio> resultPortfolios = portfolioService.getAllByUsersId(userId);

        verify(portfolioRepository, times(1)).findAllByUserId(userId);

        assertEquals(portfolios, resultPortfolios);

    }

}
