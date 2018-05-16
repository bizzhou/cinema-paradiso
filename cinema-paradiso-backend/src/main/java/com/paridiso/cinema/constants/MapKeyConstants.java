package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.map-key")
public class MapKeyConstants {
    private String movieLabel;
    private String moviePageLabel;

    public String getMovieLabel() {
        return movieLabel;
    }

    public void setMovieLabel(String movieLabel) {
        this.movieLabel = movieLabel;
    }

    public String getMoviePageLabel() {
        return moviePageLabel;
    }

    public void setMoviePageLabel(String moviePageLabel) {
        this.moviePageLabel = moviePageLabel;
    }
}
