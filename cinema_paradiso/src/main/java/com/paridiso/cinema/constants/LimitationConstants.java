package com.paridiso.cinema.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private Integer oneMonthRange;

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

    public Integer getOneMonthRange() {
        return oneMonthRange;
    }

    public void setOneMonthRange(Integer oneMonthRange) {
        this.oneMonthRange = oneMonthRange;
    }
}
