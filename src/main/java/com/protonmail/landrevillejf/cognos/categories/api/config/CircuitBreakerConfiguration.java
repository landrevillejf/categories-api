package com.protonmail.landrevillejf.cognos.categories.api.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * CircuitBreakerConfiguration configures a circuit breaker for resilience in the application.
 *
 */
@SuppressWarnings("CheckStyle")
@Configuration
public class CircuitBreakerConfiguration {

    /**
     * Creates and configures a CircuitBreaker bean.
     *
     * @return CircuitBreaker instance.
     */
    @Qualifier("cognosCircuitBreaker")
    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();

        return CircuitBreaker.of("cognosCircuitBreaker", circuitBreakerConfig);
    }
}
