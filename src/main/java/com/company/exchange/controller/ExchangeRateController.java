package com.company.exchange.controller;


import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;
import com.company.exchange.service.api.ExchangeRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * this class is used for exposing exchange rate api
 *
 * @author makari.tohid@gmail.com
 */
@RestController()
@RequestMapping("/api")
@Api(value = "controller of for exchange rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * This method is used for getting all exchange rate based on date
     *
     * @param dto consist source & traget currency and amount
     * @return
     */
    @ApiOperation(value = "This method is used for getting all exchange rate based on date")
    @PostMapping(value = "/convertExchangeRate")
    private ResponseEntity<ExchangeRateResponseDto> convertExchangeRate(
            @RequestBody @Valid ExchangeRateRequestDto dto)  {
        ExchangeRateResponseDto exchangeRateResponseDto = exchangeRateService.convertExchangeRate(dto);
        return ResponseEntity.ok(exchangeRateResponseDto);
    }



}
