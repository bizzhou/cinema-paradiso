package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NotInterestedLists", uniqueConstraints = @UniqueConstraint(columnNames = {"notInterestedListId"}))
public class NotInterestedList {

    private Integer notInterestedListId;
    private List<Movie> movies;
    private Integer notInterestedListSize;
    private List<TV> tvs;

    public NotInterestedList() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNotInterestedListId() {
        return notInterestedListId;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "NotInterestedListsMovies",
            joinColumns = {@JoinColumn(name = "notInterestedListId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<Movie> getMovies() {
        return movies;
    }

    public Integer getNotInterestedListSize() {
        return notInterestedListSize;
    }

    public void setNotInterestedListId(Integer notInterestedListId) {
        this.notInterestedListId = notInterestedListId;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setNotInterestedListSize(Integer notInterestedListSize) {
        this.notInterestedListSize = notInterestedListSize;
    }
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "NotInterestedListsTvs",
            joinColumns = {@JoinColumn(name = "notInterestedListId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<TV> getTvs() {
        return tvs;
    }

    public void setTvs(List<TV> tvs) {
        this.tvs = tvs;
    }
}
