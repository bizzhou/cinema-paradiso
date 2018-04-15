package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Carousel;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Slide;

import java.util.List;

public interface CarouselService {

    Carousel getCarousel();

    Slide updateSlide(Slide slide);

    Slide addSlide(Slide slide);

}
