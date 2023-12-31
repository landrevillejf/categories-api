package com.protonmail.landrevillejf.cognos.categories.api;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "cognosCircuitBreaker")
    public CircuitBreaker circuitBreaker() {
        return CircuitBreaker.ofDefaults("cognosCircuitBreaker");
    }
}
