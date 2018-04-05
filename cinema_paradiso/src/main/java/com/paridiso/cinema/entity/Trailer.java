package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.net.URI;

@Entity
@Table(name = "Trailers")
public class Trailer {

    public final static String trailerLocation = "/tmp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trailerId;

    // can't be Film film, Film is not an entity --> unknown entity exception
    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "imdbId", nullable = false)          // mapped with Film.imdbId
    private Movie movie;

    private String name;

    private URI path;

    public Trailer() {

    }

    public URI getPath() {
        return path;
    }

    public void setPath(URI path) {
        this.path = path;
    }


    public static String getTrailerLocation() {
        return trailerLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(Integer trailerId) {
        this.trailerId = trailerId;
    }

    public Movie getFilm() {
        return movie;
    }

    public void setFilm(Movie movie) {
        this.movie = movie;
    }

}


