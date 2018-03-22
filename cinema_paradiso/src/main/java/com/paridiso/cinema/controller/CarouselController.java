package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Carousel;
import com.paridiso.cinema.entity.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paridiso.cinema.service.CarouselService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/carousel")
@RestController
public class CarouselController {

//    @Autowired
//    CarouselService carouselService;

    @RequestMapping(value = "/" , method = GET)
    public ResponseEntity<Carousel> getCarousel() {
        return null;
    }

    @RequestMapping(value = "/" , method = POST)
    public ResponseEntity<Boolean> updateCarousel(@RequestBody final Carousel carousel) {
        return null;
    }

    @RequestMapping(value = "/slides/{id}", method = POST)
    public ResponseEntity<Boolean> updateSlide(@RequestBody final Slide slide) {
        return null;
    }

}
