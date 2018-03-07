package entity;

import java.util.Date;
import java.util.List;

public class Film {

    public static final String PHOTO_LOCATION = "/tmp";

    private Long id;
    private String title;
    private String year;
    private Rated rated;
    private Date releaseDate;
    private List<Genre> genres;
    private Celebrity director;
    private List<Celebrity> cast;
    private String plot;
    private String poster;
    private Double rating;
    private String studio;
    private List<String> photos;
    private List<Trailer> trailers;

    public static String getPhotoLocation() {
        return PHOTO_LOCATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Celebrity getDirector() {
        return director;
    }

    public void setDirector(Celebrity director) {
        this.director = director;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
