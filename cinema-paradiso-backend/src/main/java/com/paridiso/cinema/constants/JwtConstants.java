package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConstants {
    private String secret;
    private String salt;

    public String getSecret() {
        return secret;
    }

    public String getSalt() {
        return salt;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
