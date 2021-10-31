package com.company.exchange.integration;


import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
/**
 * this class is used for implementing integration test for  using TestRestTemplate
 *
 * @author makari.tohid@gmail.com
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeRateIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    public void setUp(){
        System.out.printf("init test");

    }

    /**
     * checkOkStatusOfConvertExchangeRateTest
     */
    @Test
    public void checkOkStatusOfConvertExchangeRateTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        ExchangeRateRequestDto requestDto=ExchangeRateRequestDto.builder().sourceCurrency("GBP")
                .targetCurrency("USD").amount(BigDecimal.valueOf(30)).build();
        HttpEntity<ExchangeRateRequestDto> request = new HttpEntity<>(requestDto, headers);
        final String availableCurrencyListUrl="http://localhost:"+randomServerPort+"/api/convertExchangeRate";

        ResponseEntity<ExchangeRateResponseDto> responseEntity = testRestTemplate.postForEntity(availableCurrencyListUrl,request, ExchangeRateResponseDto.class);
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }

    /**
     * checkResultOfAllExchangeRateByDate
     */
    @Test
    public void checkResultOfAllExchangeRateByDate(){

        final String availableCurrencyListUrl="http://localhost:"+randomServerPort+"/api/convertExchangeRate";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        ExchangeRateRequestDto requestDto=ExchangeRateRequestDto.builder().sourceCurrency("GBP")
                .targetCurrency("USD").amount(BigDecimal.valueOf(30)).build();
        HttpEntity<ExchangeRateRequestDto> request = new HttpEntity<>(requestDto, headers);

        ResponseEntity<ExchangeRateResponseDto> responseEntity = testRestTemplate.postForEntity(availableCurrencyListUrl,request,ExchangeRateResponseDto.class);
        Assertions.assertNotNull(responseEntity.getBody().getAmount());


    }




}
