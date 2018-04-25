package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserProfiles")
public class UserProfile {

    private Integer id;
    private String name;
    private String profileImage;
    private String biography;
    private Boolean isCritic;
    private Boolean isPrivate;

    @JsonIgnore
    private NotInterestedList notInterestedList;

    @JsonIgnore
    private WishList wishList;

    @JsonIgnore
    private List<Review> reviews;

    @JsonIgnore
    private List<Review> likedReviews;

    @JsonIgnore
    private List<UserRating> userRatings;

    public UserProfile() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getBiography() {
        return biography;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "notInterestedListId")
    public NotInterestedList getNotInterestedList() {
        return notInterestedList;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wishListId")
    public WishList getWishList() {
        return wishList;
    }

    public Boolean getCritic() {
        return isCritic;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "author")
    public List<Review> getReviews() {
        return reviews;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "UserProfileLikedReviews",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "reviewId")}
    )
    public List<Review> getLikedReviews() {
        return likedReviews;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "user")
    public List<UserRating> getUserRatings() {
        return userRatings;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setNotInterestedList(NotInterestedList notInterestedList) {
        this.notInterestedList = notInterestedList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public void setCritic(Boolean critic) {
        isCritic = critic;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setLikedReviews(List<Review> likedReviews) {
        this.likedReviews = likedReviews;
    }

    public void setUserRatings(List<UserRating> userRatings) {
        this.userRatings = userRatings;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", biography='" + biography + '\'' +
                ", notInterestedList=" + notInterestedList +
                ", wishList=" + wishList +
                ", isCritic=" + isCritic +
                ", isPrivate=" + isPrivate +
                ", reviews=" + reviews +
                ", likedReviews=" + likedReviews +
                ", userRatings=" + userRatings +
                '}';
    }
}
