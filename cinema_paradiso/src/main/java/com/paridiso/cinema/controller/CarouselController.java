package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Slide;
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

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<List<Slide>> getCarousel() {
        return new ResponseEntity<>(carouselService.getCarousel(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addSlide", method = POST)
    public ResponseEntity<Slide> setCarousel(@RequestBody Slide slide) {
        return new ResponseEntity<>(carouselService.addSlide(slide), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateSlide", method = POST)
    public ResponseEntity<Slide> updateSlide(@RequestBody Slide slide) {
        return new ResponseEntity<Slide>(carouselService.updateSlide(slide), HttpStatus.OK);
    }

}
