package com.travel.travelPalette.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${external.mofa.base-url}")
    private String mofaBaseUrl;

    @Bean
    public RestClient mofaRestClient() {
        return RestClient.builder()
                .baseUrl(mofaBaseUrl)
                .build();
    }
}