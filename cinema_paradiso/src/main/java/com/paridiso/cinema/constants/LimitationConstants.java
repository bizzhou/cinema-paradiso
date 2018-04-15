package com.paridiso.cinema.constants;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "application.limitation")
public class LimitationConstants {

    private Integer wishListSize;

    private Integer watchListSize;

    private Double trendingRating;

    private Double acceptableTrendingRating;

    private Integer leastReturns;

    private Double ratingLimit;

    private Integer threeWeeksRange;

    public LimitationConstants() { }

    public Integer getWishListSize() { return wishListSize; }

    public Integer getWatchListSize() {
        return watchListSize;
    }

    public void setWishListSize(Integer wishListSize) {
        this.wishListSize = wishListSize;
    }

    public void setWatchListSize(Integer watchListSize) {
        this.watchListSize = watchListSize;
    }

    public Double getTrendingRating() {
        return trendingRating;
    }

    public void setTrendingRating(Double trendingRating) {
        this.trendingRating = trendingRating;
    }

    public Integer getLeastReturns() {
        return leastReturns;
    }

    public void setLeastReturns(Integer leastReturns) {
        this.leastReturns = leastReturns;
    }

    public Double getAcceptableTrendingRating() {
        return acceptableTrendingRating;
    }

    public void setAcceptableTrendingRating(Double acceptableTrendingRating) {
        this.acceptableTrendingRating = acceptableTrendingRating;
    }

    public Double getRatingLimit() {
        return ratingLimit;
    }

    public void setRatingLimit(Double ratingLimit) {
        this.ratingLimit = ratingLimit;
    }

    public Integer getThreeWeeksRange() {
        return threeWeeksRange;
    }

    public void setThreeWeeksRange(Integer threeWeeksRange) {
        this.threeWeeksRange = threeWeeksRange;
    }
}
