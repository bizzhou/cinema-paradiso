package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "TVReview", uniqueConstraints = @UniqueConstraint(columnNames = {"reviewId", "imdbId"}))
public class TVReview {

    private Long reviewId;
    private UserProfile author;
    private TV tv;
    private String title;
    private Calendar postedDate;
    private boolean isCriticReview;
    private String reviewContent;
    private Double userRating;
    private Integer likeCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getReviewId() {
        return reviewId;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", nullable = false)
    public UserProfile getAuthor() {
        return author;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "imdbId", nullable = false)
    public TV getTv() {
        return tv;
    }

    public String getTitle() {
        return title;
    }

    public Calendar getPostedDate() {
        return postedDate;
    }

    public boolean isCriticReview() {
        return isCriticReview;
    }

    @Type(type = "text")
    public String getReviewContent() {
        return reviewContent;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPostedDate(Calendar postedDate) {
        this.postedDate = postedDate;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public void setCriticReview(boolean criticReview) {
        isCriticReview = criticReview;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", author=" + author +
                ", tv=" + tv +
                ", title='" + title + '\'' +
                ", postedDate=" + postedDate +
                ", isCriticReview=" + isCriticReview +
                ", reviewContent='" + reviewContent + '\'' +
                '}';
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

}
