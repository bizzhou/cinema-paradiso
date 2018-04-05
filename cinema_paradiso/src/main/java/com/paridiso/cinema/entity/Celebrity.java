package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.net.URI;

@Entity
@Table(name = "celebrities", uniqueConstraints = @UniqueConstraint(columnNames = "celebrityId"))
public class Celebrity {


    public URI PHOTO_LOCATION = new URI("/tmp/celebrity");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "celebrityId")
    private Integer celebrityId;

    @Column(name = "imdbId", unique = true)
    private String imdbId;

    @Column(name = "name")
    private String name;

    @Column(name = "profileImage")
    private String profileImage;

    @Column(name = "biography")
    private String biography;

    @Column(name = "birthDate")
    private Calendar birthDate;

    @Column(name = "birthCity")
    private String birthCity;

    @Column(name = "birthState")
    private String birthState;

    @Column(name = "birthCountry")
    private String birthCountry;

//    private List<String> photos;

    @Column(name = "isDirector")
    private boolean isDirector;


    @ManyToMany(mappedBy = "casts")
    @Column(name = "filmography")
    private Set<Movie> filmography;

    public Celebrity() throws URISyntaxException {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageName() {
        return profileImage;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImage = profileImageName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

//    public List<String> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<String> photos) {
//        this.photos = photos;
//    }

    public boolean isDirector() {
        return isDirector;
    }

    public void setDirector(boolean director) {
        isDirector = director;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public Integer getId() {
        return celebrityId;
    }

    public void setId(Integer id) {
        this.celebrityId = id;
    }

    public Set<Movie> getFilmography() {
        return filmography;
    }

    public void setFilmography(Set<Movie> filmography) {
        this.filmography = filmography;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
