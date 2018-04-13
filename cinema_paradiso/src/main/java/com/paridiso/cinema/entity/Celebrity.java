package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Celebrities", uniqueConstraints = @UniqueConstraint(columnNames = "celebrityId"))
public class Celebrity {

    private String celebrityId;

    private String name;

    private String profileImage;

    private String biography;

    private Calendar birthDate;

    private String birthCity;

    private String birthState;

    private String birthCountry;

    private Set<URI> photos;

    private boolean isDirector;

    private List<Movie> filmography;

    public Celebrity() {
    }

    @Id
    @Column(name = "celebrityId")
    public String getCelebrityId() {
        return celebrityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "profileImage")
    public String getProfileImage() {
        return profileImage;
    }

    @Column(name = "biography")
    public String getBiography() {
        return biography;
    }

    @Column(name = "birthDate")
    public Calendar getBirthDate() {
        return birthDate;
    }

    @Column(name = "birthCity")
    public String getBirthCity() {
        return birthCity;
    }

    @Column(name = "birthState")
    public String getBirthState() {
        return birthState;
    }

    @Column(name = "birthCountry")
    public String getBirthCountry() {
        return birthCountry;
    }

    @ElementCollection
    @CollectionTable(name = "CelebrityPhotos", joinColumns = @JoinColumn(name = "celebritityId"))
    @Column(name = "photos")
    public Set<URI> getPhotos() {
        return photos;
    }

    @Column(name = "isDirector")
    public boolean isDirector() {
        return isDirector;
    }

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "CelebritiesFilms",
            joinColumns = {@JoinColumn(name = "celebrityId")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    @Column(name = "filmography")
    public List<Movie> getFilmography() {
        return filmography;
    }

    public void setCelebrityId(String celebrityId) {
        this.celebrityId = celebrityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public void setPhotos(Set<URI> photos) {
        this.photos = photos;
    }

    public void setDirector(boolean director) {
        isDirector = director;
    }

    public void setFilmography(List<Movie> filmography) {
        this.filmography = filmography;
    }
}
