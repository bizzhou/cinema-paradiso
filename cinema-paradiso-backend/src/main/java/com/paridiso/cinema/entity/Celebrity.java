package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private List<Movie> filmography;

    public Celebrity() {
    }

    @Id
    public String getCelebrityId() {
        return celebrityId;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    @Column(length = 10000)
    public String getBiography() {
        return biography;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    @ElementCollection
    @CollectionTable(name = "CelebrityPhotos", joinColumns = @JoinColumn(name = "celebritityId"))
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

    @Override
    public String toString() {
        return "Celebrity{" +
                "celebrityId='" + celebrityId + '\'' +
                ", name='" + name + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", biography='" + biography + '\'' +
                ", birthDate=" + birthDate +
                ", birthCity='" + birthCity + '\'' +
                ", birthState='" + birthState + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", photos=" + photos +
                ", isDirector=" + isDirector +
                ", filmography=" + filmography +
                '}';
    }
}
