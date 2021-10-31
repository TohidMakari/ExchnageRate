package com.company.exchange.unit;

import com.company.exchange.controller.ExchangeRateController;
import com.company.exchange.dto.ExchangeRateRequestDto;
import com.company.exchange.dto.ExchangeRateResponseDto;
import com.company.exchange.service.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * this class is used for implementing web layer test for  ExchangeRateController using WebMvcTest
 *
 * @author makari.tohid@gmail.com
 */
@WebMvcTest(ExchangeRateController.class)
public class ExchangeRateControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateServiceImpl exchangeRateServiceImpl;

    /**
     * setup and init predefined values
     */
    @BeforeEach
    void setUp() {
        System.out.println("init controller unit test");
        ExchangeRateRequestDto requestDto =
                ExchangeRateRequestDto.of("USD", "GBP", BigDecimal.valueOf(30));
        ExchangeRateResponseDto responseDto = ExchangeRateResponseDto.of(BigDecimal.valueOf(4.33));
        Mockito.when(exchangeRateServiceImpl.convertExchangeRate(any())).
                thenReturn(responseDto);
    }

    /**
     * checkStatusOfGetAvailableCurrencyUnitTest
     * @throws Exception
     */
    @Test
    public void checkStatusOfGetAvailableCurrencyUnitTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/convertExchangeRate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"amount\": 30,\"sourceCurrency\": \"USD\",\"targetCurrency\": \"GBP\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(4.33));

    }


}
