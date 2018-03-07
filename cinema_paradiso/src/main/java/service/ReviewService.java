package service;

import entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAudienceReviews(Long filmId);

    List<Review> getCriticReviews(Long filmId);

    boolean addReview(Review review);

    boolean removeReview(Long reviewId);

    boolean likeReview(Long reviewId);

    boolean dislikeReview(Long reviewId);

    boolean detectBadReview(Review review);

}
