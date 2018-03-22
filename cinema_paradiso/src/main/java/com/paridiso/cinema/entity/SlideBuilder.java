package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Genre;

import java.util.List;

public final class SlideBuilder {
    private String backgroundImage;
    private Long filmId;
    private List<Celebrity> cast;
    private String plot;
    private String award;
    private Double rating;
    private Celebrity director;
    private List<Genre> genres;

    private SlideBuilder() {
    }

    public static SlideBuilder aSlide() {
        return new SlideBuilder();
    }

    public SlideBuilder withBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public SlideBuilder withFilmId(Long filmId) {
        this.filmId = filmId;
        return this;
    }

    public SlideBuilder withCast(List<Celebrity> cast) {
        this.cast = cast;
        return this;
    }

    public SlideBuilder withPlot(String plot) {
        this.plot = plot;
        return this;
    }

    public SlideBuilder withAward(String award) {
        this.award = award;
        return this;
    }

    public SlideBuilder withRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public SlideBuilder withDirector(Celebrity director) {
        this.director = director;
        return this;
    }

    public SlideBuilder withGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Slide build() {
        Slide slide = new Slide();
        slide.setBackgroundImage(backgroundImage);
        slide.setFilmId(filmId);
        slide.setCast(cast);
        slide.setPlot(plot);
        slide.setAward(award);
        slide.setRating(rating);
        slide.setDirector(director);
        slide.setGenres(genres);
        return slide;
    }
}
