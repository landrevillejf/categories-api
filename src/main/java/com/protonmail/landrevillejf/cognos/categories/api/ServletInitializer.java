package com.protonmail.landrevillejf.cognos.categories.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SuppressWarnings("CheckStyle")
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     *
     * @param application a builder for the application context
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CognosCategoriesApiApplication.class);
    }

}
