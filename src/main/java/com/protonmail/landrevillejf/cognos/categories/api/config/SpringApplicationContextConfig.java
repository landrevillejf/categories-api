package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.SpringApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for creating and configuring a SpringApplicationContext bean.
 */
@SuppressWarnings("CheckStyle")
@Configuration
public class SpringApplicationContextConfig {

    /**
     * Creates and configures a SpringApplicationContext bean.
     *
     * @return A SpringApplicationContext instance for accessing application context.
     */
    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }
}
