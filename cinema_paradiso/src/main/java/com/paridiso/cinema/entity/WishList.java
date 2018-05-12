package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WishLists", uniqueConstraints = @UniqueConstraint(columnNames = {"wishlistId"}))
public class WishList {

    private Integer wishlistId;
    private List<Movie> movies;
    private Integer wishListSize;
    private List<TV> tvs;

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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "WishListsTvs",
            joinColumns = {@JoinColumn(name = "wishListId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<TV> getTvs() {
        return tvs;
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

    public void setTvs(List<TV> tvs) {
        this.tvs = tvs;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wishlistId=" + wishlistId +
                ", movies=" + movies +
                ", wishListSize=" + wishListSize +
                '}';
    }

    

}
