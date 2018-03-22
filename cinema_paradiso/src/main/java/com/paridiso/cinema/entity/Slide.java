package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Genre;

import java.util.List;

public class Slide {

    private String backgroundImage;
    private Long filmId;
    private List<Celebrity> cast;
    private String plot;
    private String award;
    private Double rating;
    private Celebrity director;
    private List<Genre> genres;




    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public List<Celebrity> getCast() {
        return cast;
    }

    public void setCast(List<Celebrity> cast) {
        this.cast = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Celebrity getDirector() {
        return director;
    }

    public void setDirector(Celebrity director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
