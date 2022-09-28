package com.eatthefrog.UserProvisioningService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class OktaAuthHeaderFilter extends GenericFilterBean {

    @Value("${EAT.THE.FROG.OKTA.PROVISIONING.HOOK.SECRET}")
    public String OKTA_AUTH_HEADER_SECRET;

    private static final String OKTA_AUTH_HEADER_KEY = "Authorization";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader(OKTA_AUTH_HEADER_KEY);
        if (!OKTA_AUTH_HEADER_SECRET.equals(authHeader)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization failed.");
            return ;
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }
}
