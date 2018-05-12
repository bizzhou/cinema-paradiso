package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Store a rated movie and its regUserRating (of the user),
 * i.e each user can has a list of UserRatings
 */
@Entity
@Table(name = "UserTvRatings")
public class UserTvRating {

    private Integer id;
    private UserProfile userProfile;
    private TV ratedTv;
    private Double userRating;
    private Calendar ratedDate;

    public UserTvRating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userProfileId")
    public UserProfile getUser() {
        return userProfile;
    }

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "imdbId")
    public TV getRatedTv() {
        return ratedTv;
    }

    public Double getUserRating() {
        return userRating;
    }

    public Calendar getRatedDate() {
        return ratedDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRatedTv(TV tv) {
        this.ratedTv = tv;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public void setUser(UserProfile user) {
        this.userProfile = user;
    }

    public void setRatedDate(Calendar ratedDate) {
        this.ratedDate = ratedDate;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "id=" + id +
                ", userProfile=" + userProfile +
                ", ratedTv=" + ratedTv +
                ", userRating=" + userRating +
                '}';
    }

}
