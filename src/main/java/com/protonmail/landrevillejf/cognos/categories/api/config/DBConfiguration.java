/**
 * DBConfiguration is a configuration class responsible for configuring the database connection properties
 * based on the active Spring profiles (dev, test, staging, uat, prod). It uses the @Profile annotation to specify
 * different database configurations for each profile.
 */
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
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
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
     * Logger instance for logging database connection information.
     */
    private Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

    /**
     * The JDBC driver class name for the database connection.
     */
    private String driverClassName;

    /**
     * The JDBC URL for the database connection.
     */
    private String url;

    /**
     * The username for authenticating with the database.
     */
    private String username;

    /**
     * The password for authenticating with the database.
     */
    private String password;

    /**
     * Configures the database connection for the "dev" profile.
     *
     * @return A string representing the database connection information.
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
     * Configures the database connection for the "test" profile.
     *
     * @return A string representing the database connection information.
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
     * Configures the database connection for the "staging" profile.
     *
     * @return A string representing the database connection information.
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
     * Configures the database connection for the "uat" profile.
     *
     * @return A string representing the database connection information.
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
     * Configures the database connection for the "prod" profile.
     *
     * @return A string representing the database connection information.
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

