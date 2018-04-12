package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.token")
public class TokenConstants {
    private String type;
    private String header;

    public String getType() {
        return type;
    }

    public String getHeader() {
        return header;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
