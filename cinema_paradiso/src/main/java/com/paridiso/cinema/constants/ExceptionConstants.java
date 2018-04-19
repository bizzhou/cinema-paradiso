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
    private String movieDoesNotExist;
    private String celebritySaveFail;
    private String celebrityNotFound;
    private String passwordHashingFailure;
    private String carouselNotFound;
    private String slideNotFound;
    private String userRatingExists;
    private String userRatingNotExists;
    private String reviewExists;

    public String getReviewExists() {
        return reviewExists;
    }

    public void setReviewExists(String reviewExists) {
        this.reviewExists = reviewExists;
    }

    public String getUserRatingNotExists() {
        return userRatingNotExists;
    }

    public void setUserRatingNotExists(String userRatingNotExists) {
        this.userRatingNotExists = userRatingNotExists;
    }

    public String getUserRatingExists() {
        return userRatingExists;
    }

    public void setUserRatingExists(String userRatingExists) {
        this.userRatingExists = userRatingExists;
    }

    public String getCelebrityNotFound() {
        return celebrityNotFound;
    }

    public void setCelebrityNotFound(String celebrityNotFound) {
        this.celebrityNotFound = celebrityNotFound;
    }

    public String getCelebritySaveFail() {
        return celebritySaveFail;
    }

    public void setCelebritySaveFail(String celebritySaveFail) {
        this.celebritySaveFail = celebritySaveFail;
    }

    public String getMovieDoesNotExist() {
        return movieDoesNotExist;
    }

    public void setMovieDoesNotExist(String movieDoesNotExist) {
        this.movieDoesNotExist = movieDoesNotExist;
    }

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

    public String getPasswordHashingFailure() {
        return passwordHashingFailure;
    }

    public void setPasswordHashingFailure(String passwordHashingFailure) {
        this.passwordHashingFailure = passwordHashingFailure;
    }

    public String getCarouselNotFound() {
        return carouselNotFound;
    }

    public void setCarouselNotFound(String carouselNotFound) {
        this.carouselNotFound = carouselNotFound;
    }

    public String getSlideNotFound() {
        return slideNotFound;
    }

    public void setSlideNotFound(String slideNotFound) {
        this.slideNotFound = slideNotFound;
    }
}
