package com.company.exchange.dto;

import lombok.*;

import java.math.BigDecimal;
/**
 * This class is used for return response of rest api
 *
 * @author Makari.tohid@gmail.com
 *
 * @see  com.company.exchange.controller.ExchangeRateController
 */
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ExchangeRateResponseDto {

    BigDecimal amount;

}
