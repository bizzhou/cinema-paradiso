package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> getAudienceReviews(Long filmId);

    List<Review> getCriticReviews(Long filmId);

    void addReview(Integer userId, String movieId, Review review);

    Optional<Review> getReview(Long reviewId);

    Review removeReview(Integer userId, String filmId, Long reviewId);

    boolean likeReview(Long reviewId);

    boolean dislikeReview(Long reviewId);

    boolean detectBadReview(Review review);

}
