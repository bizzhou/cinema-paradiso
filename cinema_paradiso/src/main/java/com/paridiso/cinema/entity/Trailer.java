package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.net.URI;

@Entity
@Table(name = "Trailers")
public class Trailer {

    private Integer trailerId;

    private Film film;

    private String name;

    private URI path;

    public Trailer() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getTrailerId() {
        return trailerId;
    }

    // TODO: temp return type
    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "imdbId", nullable = false)
    public Movie getFilm() {
        return (Movie)film;
    }

    public String getName() {
        return name;
    }

    public URI getPath() {
        return path;
    }

    public void setTrailerId(Integer trailerId) {
        this.trailerId = trailerId;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(URI path) {
        this.path = path;
    }
}


