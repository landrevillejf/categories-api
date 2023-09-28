package com.protonmail.landrevillejf.cognos.categories.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SuppressWarnings("CheckStyle")
@Component
@Slf4j
public class LoggingFilter implements Filter {

    /**
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                     to for further processing
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        if ( this.shouldLogRequest(httpServletRequest) ) {

            log.info("Request " + httpServletRequest.getRequestURL().toString() + ", method: " + httpServletRequest.getMethod());

        }

        // pre methods call stamps
        chain.doFilter(request, response);

        // post method calls stamps
        if ( this.shouldLogRequest(httpServletRequest) ) {
            log.info("Response status: " + httpServletResponse.getStatus());
        }

    }

    /**
     *
     * @param request
     * @return
     */
    private Boolean shouldLogRequest(HttpServletRequest request) {

        return !request.getServletPath().matches("(?i).*actuator.*")
                && !request.getServletPath().matches("(?i).*swagger.*")
                && !request.getServletPath().matches("(?i).*api-docs.*");
    }

}
