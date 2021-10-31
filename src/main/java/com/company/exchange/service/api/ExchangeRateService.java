package com.company.exchange.service.api;

import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;


import java.util.Set;

/**
 * This interface consist exchange rate methods
 * @author makari.tohid@gmail.com
 */
public interface ExchangeRateService {


    ExchangeRateResponseDto convertExchangeRate(ExchangeRateRequestDto dto);


}
