package com.paridiso.cinema.entity;

public class Review {

    private Long reviewId;
    private Integer userId;
    private Long movieId;
    private Integer likeCount;
    private boolean isCriticReview;
    private String reviewContent;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isCriticReview() {
        return isCriticReview;
    }

    public void setCriticReview(boolean criticReview) {
        isCriticReview = criticReview;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
