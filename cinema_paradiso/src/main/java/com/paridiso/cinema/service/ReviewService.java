package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAudienceReviews(Long filmId);

    List<Review> getCriticReviews(Long filmId);

    void addReview(Integer userId, String movieId, Review review);

    Review getReview(Long reviewId);

    Review editReview(Integer userProfileId, Review review);

    void removeReview(Integer profileId, Long reviewId);

    List<Review> getMovieReviews(String filmId);

    boolean detectBadReview(Review review);

}
