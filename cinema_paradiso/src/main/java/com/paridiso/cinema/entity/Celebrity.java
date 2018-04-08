package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.net.URI;

@Entity
@Table(name = "Celebrities", uniqueConstraints = @UniqueConstraint(columnNames = "celebrityId"))
public class Celebrity {


    public URI PHOTO_LOCATION;

    @Id
    @Column(name = "celebrityId", unique = true)
    private String celebrityId;

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

    @ElementCollection
    @CollectionTable(name = "CelebrityPhotos", joinColumns = @JoinColumn(name = "imdbId"))
    @Column(name = "photo")
    private List<String> photos;

    @Column(name = "isDirector")
    private boolean isDirector;


    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "CelebritiesFilms",
            joinColumns = {@JoinColumn(name = "celebrityId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    private List<Movie> filmography;

    public Celebrity() {
    }


    public URI getPHOTO_LOCATION() {
        try {
            PHOTO_LOCATION = new URI("/tmp/celebrity");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return PHOTO_LOCATION;
    }

    public void setPHOTO_LOCATION(URI PHOTO_LOCATION) {
        this.PHOTO_LOCATION = PHOTO_LOCATION;
    }

    public String getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(String celebrityId) {
        this.celebrityId = celebrityId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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


    public List<Movie> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<Movie> filmography) {
        this.filmography = filmography;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
