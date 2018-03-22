package com.paridiso.cinema.entity;

import java.util.List;

public class Carousel {

    private List<Slide> carousel;

    public Carousel(List<Slide> carousel) {
        this.carousel = carousel;
    }

    public List<Slide> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<Slide> carousel) {
        this.carousel = carousel;
    }
}
