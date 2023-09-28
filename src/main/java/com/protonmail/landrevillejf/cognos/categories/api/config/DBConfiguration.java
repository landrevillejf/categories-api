package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.",email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2020-01-01",
        revision = 1,
        comments = "Author DBConfiguration"
)
@Getter
@Setter
@Profile("dev,prod")
@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
    /**
     *
     */
    private Logger logger = LoggerFactory.getLogger(DBConfiguration.class);
    /**
     *
     */
    private String driverClassName;
    /**
     *
     */
    private String url;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;

    /**
     *
     * @return
     */
    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        logger.info("DB connection for DEV");
        logger.info(driverClassName);
        logger.info(url);
        return "DB connection for DEV";
    }

    /**
     *
     * @return
     */
    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        logger.info("DB Connection to TEST - Low Cost Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to TEST - Low Cost Instance";
    }

    /**
     *
     * @return
     */
    @Profile("staging")
    @Bean
    public String stagingDatabaseConnection() {
        logger.info("DB Connection to STAGING - Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to STAGING - Instance";
    }

    /**
     *
     * @return
     */
    @Profile("uat")
    @Bean
    public String uatDatabaseConnection() {
        logger.info("DB Connection to UAT - Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to UAT - Instance";
    }

    /**
     *
     * @return
     */
    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        logger.info("DB Connection to PROD - High Performance Instance");
        logger.info(driverClassName);
        logger.info(url);
        return "DB Connection to PROD - High Performance Instance";
    }
}
