package com.company.exchange.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * This class is used for getting config  of application.properties
 *
 * @author Makari.tohid@gmail.com
 *
 */
@Component
public class AppPropertyConfig {

    @Value("${exchange.rate.currency.url}")
    public String exchangeRateCurrencyUrl;

    @Value("${exchange.rate.currency.accesskey}")
    public String exchangeRateCurrencyAccesskey;

    @Value("${exchange.rate.currency.connection.request.timeout}")
    public int exchangeRateCurrencyConnectionRequestTimeout;

    @Value("${exchange.rate.currency.connect.timeout}")
    public int exchangeRateCurrencyConnectTimeout;

    @Value("${exchange.rate.currency.read.timeout}")
    public int exchangeRateCurrencyReadTimeout;


}
