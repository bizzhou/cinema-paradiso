package com.paridiso.cinema.entity;

import javax.persistence.*;

/**
 * Store a rated movie and its rating (of the user),
 * i.e each user can has a list of UserRatings
 */
@Entity
@Table(name = "UserRatings")
public class UserRating {

    private Integer id;
    private UserProfile user;
    private Movie ratedMovie;
    private Double userRating;

    public UserRating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public UserProfile getUser() {
        return user;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "imdbId")
    public Movie getRatedMovie() {
        return ratedMovie;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRatedMovie(Movie movie) {
        this.ratedMovie = movie;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "id=" + id +
                ", user=" + user +
                ", ratedMovie=" + ratedMovie +
                ", userRating=" + userRating +
                '}';
    }
}
