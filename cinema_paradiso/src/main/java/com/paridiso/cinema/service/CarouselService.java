package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Carousel;

public interface CarouselService {

    Carousel getCarousel();

    boolean updateSlide(Slide slide);

    boolean updateCarousel(Carousel carousel);

    // Carousel buildCarousel();

}
