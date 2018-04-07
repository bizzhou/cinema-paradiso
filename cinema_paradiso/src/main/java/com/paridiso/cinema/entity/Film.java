package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Genre;
import com.paridiso.cinema.entity.enumerations.Rated;

import javax.persistence.*;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public class Film {

    public static final String PHOTO_LOCATION = "/tmp";

    @Id
    @Column(name = "imdbId", unique = true)
    private String imdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Enumerated
    @Column(name = "rated")
    private Rated rated;

    @Column(name = "releasedDate")
    private Calendar releaseDate;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "MovieGenres", joinColumns = @JoinColumn(name = "imdbId"))
    @Column(name = "genre")
    private List<Genre> genres;

    @ElementCollection
    @CollectionTable(name = "MovieAwards", joinColumns = @JoinColumn(name = "imdbId"))
    @Column(name = "award")
    private Set<String> awards;

    @ElementCollection
    @CollectionTable(name = "MoviePhotos", joinColumns = @JoinColumn(name = "imdbId"))
    @Column(name = "photo")
    private List<String> photos;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Celebrity director;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "FilmsCelebrities",
            joinColumns = {@JoinColumn(name = "imdbId")},
            inverseJoinColumns = {@JoinColumn(name = "celebrityId")}
    )
    private List<Celebrity> casts;

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "movie")
    private Set<Trailer> trailers;

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Review> reviews;

    @Column(name = "plot")
    private String plot;

    @Column(name = "language")
    private String language;

    @Column(name = "country")
    private String country;

    @Column(name = "poster")
    private String poster;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "numberOfRatings")
    private Integer numberOfRatings;

    @Column(name = "production")
    private String production;

    @Column(name = "website")
    private URI website;

    public Film() {
    }

    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public URI getWebsite() {
        return website;
    }

    public void setWebsite(URI website) {
        this.website = website;
    }

    public static String getPhotoLocation() {
        return PHOTO_LOCATION;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Celebrity getDirector() {
        return director;
    }

    public void setDirector(Celebrity director) {
        this.director = director;
    }

    public List<Celebrity> getCast() {
        return casts;
    }

    public void setCast(List<Celebrity> cast) {
        this.casts = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Set<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(Set<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Set<String> getAwards() {
        return awards;
    }

    public void setAwards(Set<String> awards) {
        this.awards = awards;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

}
