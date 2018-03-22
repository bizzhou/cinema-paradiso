package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.TV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paridiso.cinema.service.FilmService;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/tv")
@RestController
public class TVController {

//    @Autowired
//    @Qualifier("TVServiceImpl")
//    FilmService filmService;

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<List> getAllTV() {
        return null;
    }


    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<TV> getTV(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/add_tv", method = POST)
    public ResponseEntity<Boolean> addTV(@RequestBody TV tv) {
        return null;
    }


    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Boolean> deleteTV(@PathVariable Long id) {
        return null;
    }


    @RequestMapping(value = "/rate_tv/{id}", method = POST)
    public ResponseEntity<Boolean> rateTV(@PathVariable Long id,
                                          @RequestParam(value = "rating", required = true) Double rating) {
        return null;
    }

    @RequestMapping(value = "/update_tv", method = POST)
    public ResponseEntity<Boolean> updateTV(@RequestBody TV tv) {
        return null;
    }

    // TODO how to represent image ?
    @RequestMapping(value = "/{id}/update_poster", method = POST)
    public ResponseEntity<Boolean> updatePoster(@PathVariable Integer id, @RequestBody String poster) {
        return null;
    }

    @RequestMapping(value = "/{id}/update_trailer", method = POST)
    public ResponseEntity<Boolean> updateTrailer(@PathVariable Integer id, @RequestBody String trailer) {
        return null;
    }

    @RequestMapping(value = "/{id}/trailer", method = GET)
    public ResponseEntity<Boolean> getTrailers(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/trending", method = GET)
    public ResponseEntity<List> getTVsTrending() {
        return null;
    }

    @RequestMapping(value = "/playing", method = GET)
    public ResponseEntity<List> getTVsPlaying() {
        return null;
    }

    @RequestMapping(value = "/top_rating", method = GET)
    public ResponseEntity<List> getTVsTopRatings() {
        return null;
    }

    @RequestMapping(value = "/{id}/similar", method = GET)
    public ResponseEntity<List> getSimilarTVs(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/range", method = GET)
    public ResponseEntity<List> getTVsInRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return null;
    }


}
