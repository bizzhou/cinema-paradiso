package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.algorithm")
public class AlgorithmConstants {

    private String shaHashing;

    public String getShaHashing() {
        return shaHashing;
    }

    public void setShaHashing(String shaHashing) {
        this.shaHashing = shaHashing;
    }
}
