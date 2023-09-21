package com.protonmail.landrevillejf.cognos.categories.api.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Profile("dev,prod")
@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
    Logger logger = LoggerFactory.getLogger(DBConfiguration.class);
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        logger.info("DB connection for DEV");
        logger.info(driverClassName);
        logger.info(url);
        return "DB connection for DEV";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        logger.info("DB Connection to TEST - Low Cost Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to TEST - Low Cost Instance";
    }

    @Profile("staging")
    @Bean
    public String stagingDatabaseConnection() {
        logger.info("DB Connection to STAGING - Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to STAGING - Instance";
    }

    @Profile("uat")
    @Bean
    public String uatDatabaseConnection() {
        logger.info("DB Connection to UAT - Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to UAT - Instance";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        logger.info("DB Connection to PROD - High Performance Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to PROD - High Performance Instance";
    }
}
