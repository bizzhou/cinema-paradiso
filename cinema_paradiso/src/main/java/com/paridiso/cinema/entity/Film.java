package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paridiso.cinema.entity.enumerations.Genre;
import com.paridiso.cinema.entity.enumerations.Rated;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

// TODO: show have a way to count # of 5-star ratings, 4-star, 3-star...
@MappedSuperclass
public class Film {

    protected String imdbId;
    protected String title;
    protected String year;
    protected Rated rated;
    protected Calendar releaseDate;
    protected Set<Genre> genres;
    protected Set<String> awards;
    protected Set<URI> photos;
    protected Celebrity director;
    protected List<Celebrity> casts;
    protected List<Review> reviews;
    protected Set<Trailer> trailers;
    protected String plot;
    protected String movieInfo;
    protected String language;
    protected String country;
    protected String poster;
    protected Double rating;
    protected Integer numberOfRatings;
    protected String production;
    protected URI website;

    public Film() {
    }

    @Id
    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    @Enumerated
    public Rated getRated() {
        return rated;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "MovieGenres", joinColumns = @JoinColumn(name = "imdbId"))
    public Set<Genre> getGenres() {
        return genres;
    }

    @ElementCollection
    @CollectionTable(name = "MovieAwards", joinColumns = @JoinColumn(name = "imdbId"))
    public Set<String> getAwards() {
        return awards;
    }

    @ElementCollection
    @CollectionTable(name = "MoviePhotos", joinColumns = @JoinColumn(name = "imdbId"))
    public Set<URI> getPhotos() {
        return photos;
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "directorId")
    public Celebrity getDirector() {
        return director;
    }

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "FilmsCelebrities",
            joinColumns = {@JoinColumn(name = "imdbId")},
            inverseJoinColumns = {@JoinColumn(name = "celebrityId")}
    )
    public List<Celebrity> getCasts() {
        return casts;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "film")
    public Set<Trailer> getTrailers() {
        return trailers;
    }

//    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "movie")
    public List<Review> getReviews() {
        return reviews;
    }

    public String getPlot() {
        return plot;
    }

    @Type(type = "text")
    public String getMovieInfo() {
        return movieInfo;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getPoster() {
        return poster;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public String getProduction() {
        return production;
    }

    public URI getWebsite() {
        return website;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setAwards(Set<String> awards) {
        this.awards = awards;
    }

    public void setPhotos(Set<URI> photos) {
        this.photos = photos;
    }

    public void setDirector(Celebrity director) {
        this.director = director;
    }

    public void setCasts(List<Celebrity> casts) {
        this.casts = casts;
    }

    public void setTrailers(Set<Trailer> trailers) {
        this.trailers = trailers;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setWebsite(URI website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Film{" +
                "imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated=" + rated +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", awards=" + awards +
                ", photos=" + photos +
                ", director=" + director +
                ", casts=" + casts +
                ", trailers=" + trailers +
                ", reviews=" + reviews +
                ", plot='" + plot + '\'' +
                ", movieInfo='" + movieInfo + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", poster='" + poster + '\'' +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                ", production='" + production + '\'' +
                ", website=" + website +
                '}';
    }
}
