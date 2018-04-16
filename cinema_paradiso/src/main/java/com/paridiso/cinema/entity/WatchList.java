package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WatchLists")
public class WatchList {

    private Integer watchListId;
    private List<Movie> movies;
    private Integer wishListSize;

    public WatchList() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getWatchListId() {
        return watchListId;
    }

    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            name = "WatchListsMovies",
            joinColumns = {@JoinColumn(name = "watchListId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<Movie> getMovies() {
        return movies;
    }

    public Integer getWishListSize() { return wishListSize; }

    public void setWatchListId(Integer watchListId) {
        this.watchListId = watchListId;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setWishListSize(Integer wishListSize) { this.wishListSize = wishListSize; }

    @Override
    public String toString() {
        return "WatchList{" +
                "watchListId=" + watchListId +
                ", movies=" + movies +
                ", wishListSize=" + wishListSize +
                '}';
    }
}
