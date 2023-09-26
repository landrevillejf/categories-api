package com.protonmail.landrevillejf.cognos.categories.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping(Api.API_URI_PATH)
                .allowedOrigins(Api.CROSS_ORIGINS)
                .allowedMethods(Api.GET, Api.POST, Api.PUT, Api.DELETE)
                .allowedHeaders(Api.ALLOWED_HEADERS);
    }
}
