package com.isep.acme.domain.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

// Code based on following tutorial https://attacomsian.com/blog/http-requests-resttemplate-spring-boot

@Service
@AllArgsConstructor
public class RestService {

    private final RestTemplate restTemplate;

    public String generateFunFact(LocalDate date) {
        String url = "http://numbersapi.com/{month}/{day}/date";
        return this.restTemplate.getForObject(url, String.class, date.getMonthValue(),date.getDayOfMonth());
    }
}
