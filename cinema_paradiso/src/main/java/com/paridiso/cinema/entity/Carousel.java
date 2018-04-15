package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Carousels")
public class Carousel {

    private Integer id;

    private List<Movie> carouselMovies;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CarouselMovies",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "imdbId")}
    )
    public List<Movie> getCarouselMovies() {
        return carouselMovies;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarouselMovies(List<Movie> carouselMovies) {
        this.carouselMovies = carouselMovies;
    }

}
