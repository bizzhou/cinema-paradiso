package com.paridiso.cinema.security;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.enumerations.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;

    public User validate(String token) {

        User jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();
            jwtUser.setUserID(Integer.parseInt(body.get("id").toString()));
            jwtUser.setUsername(body.get("username").toString());
            jwtUser.setRole(Role.valueOf(body.get("role").toString()));
            jwtUser.setEmail(body.get("email").toString());

            if (body.get("profileId").toString() != null) {
                UserProfile userProfile = new UserProfile();
                userProfile.setId(Integer.parseInt(body.get("profileId").toString()));
                jwtUser.setUserProfile(userProfile);
            }

        } catch (Exception e) {
            System.out.println("Cannot verify the token");
        }

        return jwtUser;

    }
}
