package com.paridiso.cinema.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.constants.TokenConstants;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    private TokenConstants tokenConstants;

    public Integer getUserIdFromToken(String jwtToken) {
        int headerLength = tokenConstants.getType().length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));
        return validatedUser.getUserID();
    }

}
