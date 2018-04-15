package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Carousels")
public class Carousel {

    private Integer id;

    private List<Slide> carouselSlides;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CarouselSlides",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "slideId")}
    )
    public List<Slide> getCarouselSlides() {
        return carouselSlides;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarouselSlides(List<Slide> carouselSlides) {
        this.carouselSlides = carouselSlides;
    }

}
