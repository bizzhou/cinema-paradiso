package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Slide;

import java.util.List;

public interface CarouselService {

    List<Slide> getCarousel();

    Slide updateSlide(Slide slide);

    Slide addSlide(Slide slide);

    List<Slide> setMovieStatus(List<Slide> slides, Integer userId);

}
