package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.exception")
public class ExceptionConstants {

    private String userNotFound;
    private String profileNotFound;
    private String userExists;
    private String movieExists;
    private String movieNotFound;
    private String resourceNotFound;
    private String reviewNotFound;

    public String getUserNotFound() {
        return userNotFound;
    }

    public String getProfileNotFound() {
        return profileNotFound;
    }

    public String getUserExists() {
        return userExists;
    }

    public String getMovieExists() {
        return movieExists;
    }

    public String getMovieNotFound() {
        return movieNotFound;
    }

    public String getResourceNotFound() {
        return resourceNotFound;
    }

    public String getReviewNotFound() {
        return reviewNotFound;
    }

    public void setUserNotFound(String userNotFound) {
        this.userNotFound = userNotFound;
    }

    public void setProfileNotFound(String profileNotFound) {
        this.profileNotFound = profileNotFound;
    }

    public void setUserExists(String userExists) {
        this.userExists = userExists;
    }

    public void setMovieExists(String movieExists) {
        this.movieExists = movieExists;
    }

    public void setMovieNotFound(String movieNotFound) {
        this.movieNotFound = movieNotFound;
    }

    public void setResourceNotFound(String resourceNotFound) {
        this.resourceNotFound = resourceNotFound;
    }

    public void setReviewNotFound(String reviewNotFound) {
        this.reviewNotFound = reviewNotFound;
    }
}
