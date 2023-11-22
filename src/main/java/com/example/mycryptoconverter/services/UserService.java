package com.example.mycryptoconverter.services;

import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.models.Portfolio;
import com.example.mycryptoconverter.models.Role;
import com.example.mycryptoconverter.models.User;
import com.example.mycryptoconverter.repository.CryptoRepository;
import com.example.mycryptoconverter.repository.PortfolioRepository;
import com.example.mycryptoconverter.repository.RoleRepository;
import com.example.mycryptoconverter.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
    private final PortfolioService portfolioService;
    private final CryptoService cryptoService;
    private final PortfolioRepository portfolioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User findByUsername(String name){
        return userRepository.findByUsername(name).orElseThrow(()-> new UsernameNotFoundException("Пользователь с таким именем не найден"));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserService userService;
        return userRepository.save(user);

    }

    public List<Portfolio> getAllPortfolio(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        cryptoService.updateAllCrypto();
        portfolioService.updateTotalPrice();

        return portfolioRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user){
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }
}
