package ru.dmitryivanov.PP_3_1_6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
