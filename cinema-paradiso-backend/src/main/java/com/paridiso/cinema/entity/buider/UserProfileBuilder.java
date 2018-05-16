package com.paridiso.cinema.entity.buider;

import com.paridiso.cinema.entity.*;

import java.util.List;

public final class UserProfileBuilder {
    private Integer id;
    private String name;
    private String profileImage;
    private String biography;
    private NotInterestedList notInterestedList;
    private WishList wishList;
    private List<Review> reviews;

    public UserProfileBuilder() {
    }

    public static UserProfileBuilder anUserProfile() {
        return new UserProfileBuilder();
    }

    public UserProfileBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserProfileBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserProfileBuilder withProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public UserProfileBuilder withBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public UserProfileBuilder withNotInterestedListList(NotInterestedList notInterestedList) {
        this.notInterestedList = notInterestedList;
        return this;
    }

    public UserProfileBuilder withWishList(WishList wishList) {
        this.wishList = wishList;
        return this;
    }

    public UserProfileBuilder withReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public UserProfile build() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setName(name);
        userProfile.setProfileImage(profileImage);
        userProfile.setBiography(biography);
        userProfile.setNotInterestedList(notInterestedList);
        userProfile.setWishList(wishList);
        userProfile.setReviews(reviews);
        return userProfile;
    }
}
