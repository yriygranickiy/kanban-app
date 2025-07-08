package com.example.get_away.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient authServiceWebClient() {
        return WebClient.builder()
              .baseUrl("http://localhost:8082").build();
    }
}
