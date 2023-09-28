package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.filter.LoggingFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("CheckStyle")
@AllArgsConstructor
@Configuration
public class FiltersConfig {
    /**
     *
     */
    private final LoggingFilter loggingFilter;

    /**
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean loggingFilterBean() {

        final FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(loggingFilter);
        filterBean.addUrlPatterns("/*");
        // Lower values have higher priority
        filterBean.setOrder(Integer.MAX_VALUE-2);

        return filterBean;
    }

}
