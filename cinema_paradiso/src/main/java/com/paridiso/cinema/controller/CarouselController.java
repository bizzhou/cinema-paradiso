package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Slide;
import com.paridiso.cinema.service.CarouselService;
import com.paridiso.cinema.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/carousel")
@RestController
@CrossOrigin(origins = "*")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @Autowired
    JwtTokenService jwtTokenService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Slide>> getCarousel() {
        return new ResponseEntity<>(carouselService.getCarousel(), HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomCarousel")
    public ResponseEntity<List<Slide>> getCustomCarousel(@RequestHeader(value = "Authorization") String jwtToken) {
        System.out.println("Fetching custom carousel");

        Integer userId = jwtTokenService.getUserProfileIdFromToken(jwtToken);

        List<Slide> slides = carouselService.setInitialMovieStatus(carouselService.getCarousel(), userId);
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
