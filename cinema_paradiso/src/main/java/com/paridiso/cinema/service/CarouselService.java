package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Carousel;
import com.paridiso.cinema.entity.Movie;

import java.util.List;

public interface CarouselService {

    Carousel getCarousel();

    boolean updateCarousel(Carousel carousel);

    void setCarousel(Carousel carousel);

}
