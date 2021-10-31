package com.company.exchange.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 * This class is used for create bean of restTemplate
 *
 * @author Makari.tohid@gmail.com
 *
 */
@Configuration
public class AppConfig {

    @Bean("ExchangeRateRestTemplate")
    public RestTemplate restTemplate()
    {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(50000);
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(50000);
        return new RestTemplate(httpRequestFactory);
    }

}
