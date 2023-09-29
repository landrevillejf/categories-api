/**
 * Configuration class for configuring the OpenAPI documentation for the API.
 */
package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2023-09-01",
        revision = 1,
        comments = "Author OpenAPIConfig"
)
@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Value("${openapi.prod-url}")
    private String prodUrl;

    /**
     * Creates and configures the OpenAPI documentation bean.
     *
     * @return An OpenAPI instance representing the API documentation.
     */
    @Bean
    public OpenAPI myOpenAPI() {
        // Create server configurations for Development and Production environments
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        // Create contact information for the API
        Contact contact = new Contact();
        contact.setEmail("landrevillejf@protonmail.com");
        contact.setName("Jean-Francois Landreville");
        contact.setUrl("https://www.lanaforge.ca");

        // Create MIT License information
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        // Create API information
        Info info = new Info()
                .title("Cognos E-Learning Categories API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Cognos E-learning courses categories.")
                .termsOfService("https://www.lanaforge.com/cognos/terms")
                .license(mitLicense);

        // Create and configure the OpenAPI documentation
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}

