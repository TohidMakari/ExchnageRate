package com.company.exchange.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * This class is used for getting request of rest api
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
public class ExchangeRateRequestDto {

    @Size(min = 3,max = 3,message = "sourceCurrency should be 3 character")
    String sourceCurrency;

    @Size(min = 3,max = 3,message = "targetCurrency should be 3 character")
    String targetCurrency;

    @Digits(integer = 15,fraction = 3,message = "fix range of amount")
    @NotNull(message = "amount can not be null")
    BigDecimal amount;

}
