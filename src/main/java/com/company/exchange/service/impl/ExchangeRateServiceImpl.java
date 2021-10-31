package com.company.exchange.service.impl;

import com.company.exchange.common.config.AppPropertyConfig;
import com.company.exchange.exception.AppException;
import com.company.exchange.dto.CurrencyDto;
import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;
import com.company.exchange.exception.InvalidCurenncyException;
import com.company.exchange.service.api.ExchangeRateService;
import jdk.net.SocketFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * this class is used for implementing ExchangeRateService
 *
 * @author makari.tohid@gmail.com
 * @see com.company.exchange.service.api.ExchangeRateService
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    private final AppPropertyConfig appPropertyConfig;

    private final RestTemplate restTemplate;

    public ExchangeRateServiceImpl(AppPropertyConfig appPropertyConfig, RestTemplate restTemplate) {
        this.appPropertyConfig = appPropertyConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public ExchangeRateResponseDto convertExchangeRate(ExchangeRateRequestDto dto) {

        LOGGER.info("input parameters before call web service", dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<CurrencyDto> response;
        try {
            response = restTemplate
                    .exchange(appPropertyConfig.exchangeRateCurrencyUrl + appPropertyConfig.exchangeRateCurrencyAccesskey
                            , HttpMethod.POST, httpEntity, CurrencyDto.class);
            LOGGER.info("input parameters before call web service", response);
        } catch (HttpStatusCodeException exs) {
            LOGGER.error("input parameters before call web service", exs);
            throw new AppException(exs.getMessage());

        } catch (RestClientException restClientException) {
            LOGGER.error("input parameters before call web service", restClientException);
            throw new AppException(restClientException.getMessage());

        }if(!Objects.equals(response.getStatusCode(), HttpStatus.OK)){
            throw new AppException("not available");
        }
        Map<String, BigDecimal> rates = response.getBody().getRates();
        if (Objects.equals(rates.get(dto.getSourceCurrency()), null)) {
            throw new InvalidCurenncyException("invalid source currenncy exception");
        }
        if (Objects.equals(rates.get(dto.getTargetCurrency()), null)) {
            throw new InvalidCurenncyException("invalid target currenncy exception");
        }
      BigDecimal resultAmount= rates.get(dto.getSourceCurrency())
               .divide(rates.get(dto.getTargetCurrency()),14, RoundingMode.FLOOR).multiply(dto.getAmount());

        return ExchangeRateResponseDto.of(resultAmount.setScale(2,RoundingMode.FLOOR));
    }
}
