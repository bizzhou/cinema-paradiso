package com.paridiso.cinema.security;

import com.paridiso.cinema.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;
    // 60 Minutes expiration time
    private static final long expirationInMills = 3600000;

    public String generate(User jwtUser) {

//        Date expirationTime = new Date(System.currentTimeMillis() + expirationInMills);

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
//                .setExpiration(expirationTime);

        claims.put("username", jwtUser.getUsername());
        claims.put("role", jwtUser.getRole().name());
        claims.put("id", jwtUser.getUserID());
        claims.put("profileId", jwtUser.getUserProfile().getId());
        claims.put("email", jwtUser.getEmail());
        claims.put("profileImage", jwtUser.getUserProfile().getProfileImage());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }


}
