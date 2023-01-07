package com.aeflheim.quasar.config.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfTokenLoggerFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CsrfTokenLoggerFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;

        var httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("X-CSRF-TOKEN", token.getToken());

        logger.info("CSRF token " + token.getToken());

        filterChain.doFilter(request, httpResponse);

    }

}
