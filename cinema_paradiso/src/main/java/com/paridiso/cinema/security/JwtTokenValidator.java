package com.paridiso.cinema.security;

import com.paridiso.cinema.entity.enumerations.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;

    public TokenDetail validate(String token) {
        TokenDetail jwtUser = null;
        Claims body = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
        try {
            jwtUser = new TokenDetail();
            jwtUser.setEmail(body.get("email").toString());
            jwtUser.setUsername(body.get("username").toString());
            jwtUser.setRole(Role.valueOf(body.get("role").toString()));
            jwtUser.setUserId(Integer.parseInt(body.get("userId").toString()));
            jwtUser.setProfileId(Integer.parseInt(body.get("profileId").toString()));
        } catch (Exception e) {
            System.out.println("CANNOT VERIFY TOKEN");
        }
        return jwtUser;

    }
}
