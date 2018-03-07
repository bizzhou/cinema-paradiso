package service;

import entity.Carousel;
import entity.Slide;

public interface CarouselService {

    Carousel getCarousel();

    boolean updateSlide(Slide slide);

    boolean updateCarousel(Carousel carousel);

    // Carousel buildCarousel();

}
