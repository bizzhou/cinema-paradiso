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
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;

    @Id
    @Column(name = "imdbId", unique = true)
    private String imdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "rated")
    private Rated rated;

    @Column(name = "releasedDate")
    private Calendar releaseDate;


//    private List<Genre> genres;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Celebrity director;

    @ManyToMany(cascade = { CascadeType.MERGE})
    @JoinTable(
            name = "JoinFilmsCelebrities",
            joinColumns = {@JoinColumn(name = "filmId")},
            inverseJoinColumns = {@JoinColumn(name = "celebrityId")}
    )
    private List<Celebrity> casts;

    @OneToMany(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER, mappedBy = "movie")
    private Set<Trailer> trailers;

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Review> reviews;

    @Column(name = "plot")
    private String plot;

    @Column(name = "language")
    private String language;

    @Column(name = "country")
    private String country;

    //    private Set<String> awards;
    @Column(name = "poster")
    private String poster;

    @Column(name = "rating")
    private Double rating;

//    private List<String> photos;

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

//    public Set<String> getAwards() {
//        return awards;
//    }
//
//    public void setAwards(Set<String> awards) {
//        this.awards = awards;
//    }

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

//    public Integer getId() {
//        return filmId;
//    }
//
//    public void setId(Integer id) {
//        this.filmId = id;
//    }

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

//    public List<Genre> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }

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


//    public List<String> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<String> photos) {
//        this.photos = photos;
//    }
//
//    public List<Trailer> getTrailers() {
//        return trailers;
//    }
//
//    public void setTrailers(List<Trailer> trailers) {
//        this.trailers = trailers;
//    }
}
