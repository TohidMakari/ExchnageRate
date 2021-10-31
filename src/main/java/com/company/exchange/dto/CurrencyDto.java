package com.company.exchange.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * This class is used for getting response of restTemplate
 *
 * @author Makari.tohid@gmail.com
 *
 * @see  com.company.exchange.service.impl.ExchangeRateServiceImpl
 */
@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CurrencyDto {

    private boolean success;

    private Integer timestamp;

    private String base;

    private String date;

    private Map<String, BigDecimal> rates;

}
