package com.protonmail.landrevillejf.cognos.categories.api.cucumber;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@TestConfiguration
public class CucumberTestConfig {

    /*@Primary
    @Bean
    @Scope("cucumber-glue") // This makes the bean available to Cucumber glue code
    public RestTemplate testRestTemplate() {
        // Set the base URI to match your test environment's URL
        return new RestTemplateBuilder().rootUri("http://localhost:9090/cognos-categories-api").build();
    }*/
}
