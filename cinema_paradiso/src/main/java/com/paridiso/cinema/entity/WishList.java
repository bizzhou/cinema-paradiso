package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WishLists", uniqueConstraints = @UniqueConstraint(columnNames = {"wishlistId"}))
public class WishList {

    private Integer wishlistId;
    private List<Movie> movies;
    private Integer wishListSize;

    public WishList() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getWishlistId() {
        return wishlistId;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "WishListsMovies",
            joinColumns = {@JoinColumn(name = "wishListId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<Movie> getMovies() {
        return movies;
    }

    public Integer getWishListSize() {
        return wishListSize;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public void setWishListSize(Integer wishListSize) {
        this.wishListSize = wishListSize;
    }
}
