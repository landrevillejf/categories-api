package com.protonmail.landrevillejf.cognos.categories.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class responsible for handling requests related to the SWAGGER-UI home page.
 */
@SuppressWarnings("CheckStyle")

@Controller
public class HomeController {

    /**
     * Handles a GET request to the root ("/") URL and redirects to the Swagger UI HTML page.
     *
     * @return A redirection to the Swagger UI HTML page.
     */
    @GetMapping("/")
    public String index() {
        // Using "redirect:" to serve the Swagger UI HTML page
        return "redirect:/swagger-ui/index.html";
    }
}

