package com.company.exchange.unit;

import com.company.exchange.common.config.AppPropertyConfig;
import com.company.exchange.dto.CurrencyDto;
import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;
import com.company.exchange.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;


/**
 * this class is used for implementing unit test for  ExchangeRateService using mockito
 *
 * @author makari.tohid@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceUnitTest {

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateServiceImpl;

    @Mock
    private AppPropertyConfig appPropertyConfig;

    @Mock
    private RestTemplate restTemplate;

    /**
     * setup and init predefined values
     */
    @BeforeEach
    void setUp() {
        System.out.println("* ExchangeRateServiceUnitTest *");
        ReflectionTestUtils.setField(appPropertyConfig, "exchangeRateCurrencyUrl", "url");
        ReflectionTestUtils.setField(appPropertyConfig, "exchangeRateCurrencyAccesskey", "wsdfghjjxxx");
        ReflectionTestUtils.setField(appPropertyConfig, "exchangeRateCurrencyConnectionRequestTimeout", 30000);
        ReflectionTestUtils.setField(appPropertyConfig, "exchangeRateCurrencyConnectTimeout", 30000);
        ReflectionTestUtils.setField(appPropertyConfig, "exchangeRateCurrencyReadTimeout", 30000);

    }

    /**
     * test AvailableCurrency
     */
    @Test
    public void CheckAvailableCurrencyTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        CurrencyDto currencyDto = new CurrencyDto();
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("AED", BigDecimal.valueOf(1.2));
        rates.put("USD", BigDecimal.valueOf(1.8));
        currencyDto.setRates(rates);
        Mockito.when(restTemplate.exchange(appPropertyConfig.exchangeRateCurrencyUrl + appPropertyConfig.exchangeRateCurrencyAccesskey
                , HttpMethod.POST, httpEntity, CurrencyDto.class)).thenReturn(new ResponseEntity(currencyDto, HttpStatus.OK));
        ExchangeRateRequestDto dto = ExchangeRateRequestDto.of("AED", "USD", BigDecimal.valueOf(30));
        ExchangeRateResponseDto response = exchangeRateServiceImpl.convertExchangeRate(dto);
        Assertions.assertNotNull(response);

    }


}
