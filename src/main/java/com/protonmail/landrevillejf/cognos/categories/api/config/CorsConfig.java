/**
 * CorsConfig is a configuration class for handling Cross-Origin Resource Sharing (CORS) in the application.
 * It implements the WebMvcConfigurer interface to configure CORS settings.
 */
package com.protonmail.landrevillejf.cognos.categories.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings("CheckStyle")
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configures CORS settings for specific API endpoints.
     *
     * @param registry The CorsRegistry instance used to register CORS configurations.
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping(Api.API_URI_PATH)
                .allowedOrigins(Api.CROSS_ORIGINS)
                .allowedMethods(Api.GET, Api.POST, Api.PUT, Api.DELETE)
                .allowedHeaders(Api.ALLOWED_HEADERS);
    }
}

