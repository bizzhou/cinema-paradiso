package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.JwtConstants;
import com.paridiso.cinema.constants.TokenConstants;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.security.TokenDetail;
import com.paridiso.cinema.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    private TokenConstants tokenConstants;

    @Autowired
    private ExceptionConstants exceptionConstants;

    // TODO: if token is null
    @Override
    public Integer getUserIdFromToken(String jwtToken) {
        return getTokenDetail(jwtToken).getUserId();
    }

    private TokenDetail getTokenDetail(String jwtToken) {
        int headerLength = tokenConstants.getType().length();
        return validator.validate(jwtToken.substring(headerLength));
    }

    @Override
    public Integer getUserProfileIdFromToken(String jwtToken) {
        TokenDetail validatedUser = getTokenDetail(jwtToken);
        if (validatedUser.getProfileId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionConstants.getProfileNotFound());
        }
        return validatedUser.getProfileId();
    }

    @Override
    public String getUserEmail(String jwtToken) {
        return getTokenDetail(jwtToken).getEmail();
    }

}
