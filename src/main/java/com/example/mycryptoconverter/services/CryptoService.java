package com.example.mycryptoconverter.services;

import com.example.mycryptoconverter.models.Crypto;
import com.example.mycryptoconverter.repository.CryptoRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CryptoService {
    private final Gson json;
    private final RestTemplate restTemplate;
    private static String API_ACCESS_KEY = System.getenv("API_ACCESS_KEY");
    private final CryptoRepository cryptoRepository;

    private String jsonRequest = "http://api.coinlayer.com/live?access_key=" + API_ACCESS_KEY;


    public Crypto getCryptoByName(String name){
        jsonRequest+="&symbols="+name;
        String jsonResponse =restTemplate.getForEntity(jsonRequest,String.class).getBody();
        Crypto crypto = json.fromJson(jsonResponse,Crypto.class);

        return crypto;
    }

    @Scheduled(fixedRate = 1800000)
    public ResponseEntity<HttpStatus> updateAllCrypto(){

        String jsonResponse =restTemplate.getForEntity(jsonRequest,String.class).getBody();
        Crypto crypto = json.fromJson(jsonResponse,Crypto.class);

        LinkedHashMap<String, Object> map= crypto.getRates();

        for (String key:map.keySet()){
            Crypto newCrypto = new Crypto(crypto.getTimestamp(),crypto.getTarget());

            newCrypto.setName(key);
            newCrypto.setPrice((Double) map.get(key));
            newCrypto.setId(cryptoRepository.findByName(key).getId());

            save(newCrypto);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> save(Crypto crypto) {
        cryptoRepository.save(crypto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Cacheable
    public List<Crypto> getAll() {
        return cryptoRepository.findAll();
    }


}
