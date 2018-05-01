package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Slide;
import com.paridiso.cinema.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/carousel")
@RestController
@CrossOrigin(origins = "*")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @GetMapping(value = "/get/{loginStatus}")
    public ResponseEntity<List<Slide>> getCarousel(@PathVariable boolean loginStatus) {
        List<Slide> slides = carouselService.getCarousel();

        return new ResponseEntity<>(slides, HttpStatus.OK);
    }

    @PostMapping(value = "/add/slide")
    public ResponseEntity<Slide> setCarousel(@RequestBody Slide slide) {
        return new ResponseEntity<>(carouselService.addSlide(slide), HttpStatus.OK);
    }

    @PostMapping(value = "/update/slide")
    public ResponseEntity<Slide> updateSlide(@RequestBody Slide slide) {
        return new ResponseEntity<Slide>(carouselService.updateSlide(slide), HttpStatus.OK);
    }

}
