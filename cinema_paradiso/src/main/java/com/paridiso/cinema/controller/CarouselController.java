package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Carousel;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/carousel")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<Boolean> updateCarousel(@RequestBody final Carousel carousel) {
        return null;
    }

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<Carousel> getCarousel() {
        return new ResponseEntity<>(carouselService.getCarousel(), HttpStatus.OK);
    }

    @RequestMapping(value = "/set", method = POST)
    public ResponseEntity<Boolean> setCarousel(@RequestBody Carousel carousel) {
        carouselService.setCarousel(carousel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
