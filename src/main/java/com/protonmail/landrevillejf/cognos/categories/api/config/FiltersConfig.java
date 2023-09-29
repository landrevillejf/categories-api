/**
 * FiltersConfig is a configuration class responsible for configuring and registering filters
 * for request and response handling in the Spring Boot application. It specifically registers
 * the LoggingFilter to capture and log request and response information.
 */
package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.filter.LoggingFilter;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2023-01-01",
        revision = 1,
        comments = "Author FiltersConfig"
)
@AllArgsConstructor
@Configuration
public class FiltersConfig {
    /**
     * The LoggingFilter instance to be registered for request and response logging.
     */
    private final LoggingFilter loggingFilter;

    /**
     * Creates and configures a FilterRegistrationBean for the LoggingFilter.
     *
     * @return A FilterRegistrationBean configured for the LoggingFilter.
     */
    @Bean
    public FilterRegistrationBean loggingFilterBean() {
        final FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(loggingFilter);
        filterBean.addUrlPatterns("/*");
        // Lower values have higher priority, so set a high order value
        filterBean.setOrder(Integer.MAX_VALUE - 2);

        return filterBean;
    }
}

