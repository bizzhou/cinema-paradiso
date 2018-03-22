package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Review;
import com.paridiso.cinema.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<Review> getAudienceReviews(Long filmId) {
        return null;
    }

    @Override
    public List<Review> getCriticReviews(Long filmId) {
        return null;
    }

    @Override
    public boolean addReview(Review review) {
        return false;
    }

    @Override
    public boolean removeReview(Long reviewId) {
        return false;
    }

    @Override
    public boolean likeReview(Long reviewId) {
        return false;
    }

    @Override
    public boolean dislikeReview(Long reviewId) {
        return false;
    }

    @Override
    public boolean detectBadReview(Review review) {
        return false;
    }
}
