package com.company.exchange.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;


/**
 * config for swagger
 *
 * @author Makari.tohid@gmail.com
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "license";
    private static final String TITLE = " REST API";
    private static final String DESCRIPTION = "RESTFul API";

    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_12)
                .groupName("api service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company"))
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build().forCodeGeneration(true).apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("xAuthToken", "xAuthToken", "header");
    }


}
