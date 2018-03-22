package com.paridiso.cinema.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLogInTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private Environment env;

    public JwtLogInTokenFilter() {
        super("/**/**/protected/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws org.springframework.security.core.AuthenticationException {

        String tokenHeader = env.getProperty("token.header");
        String tokenType = env.getProperty("token.type");
        String header = httpServletRequest.getHeader(tokenHeader);

        if (header == null || !header.startsWith(tokenType)) {
            throw new RuntimeException("JWT token is missing");
        }

        String authToken = header.substring(tokenType.length());
        JwtToken token = new JwtToken(authToken);

        return getAuthenticationManager().authenticate(token);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

}
