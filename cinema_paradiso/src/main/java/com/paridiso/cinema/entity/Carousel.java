package com.paridiso.cinema.entity;

import java.util.List;
import java.util.Set;

public class Carousel {

    private Set<Slide> carousel;

    public Carousel(Set<Slide> carousel) {
        this.carousel = carousel;
    }

    public Set<Slide> getCarousel() {
        return carousel;
    }

    public void setCarousel(Set<Slide> carousel) {
        this.carousel = carousel;
    }
}
