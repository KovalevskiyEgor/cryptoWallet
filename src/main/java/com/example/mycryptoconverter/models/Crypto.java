package com.example.mycryptoconverter.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Boolean success;
    private Integer timestamp;//TODO
    private String target;

    @Transient
    private LinkedHashMap<String, Object> rates= new LinkedHashMap<>();

    private String name;
    private Double price;



    public Crypto(Integer timestamp, String target) {
        this.timestamp=timestamp;
        this.target=target;
    }
}
