package com.paridiso.cinema.service;

public interface JwtTokenService {

    Integer getUserIdFromToken(String jwtToken);

    Integer getUserProfileIdFromToken(String jwtToken);

    String getUserEmail(String jwtToken);

}
