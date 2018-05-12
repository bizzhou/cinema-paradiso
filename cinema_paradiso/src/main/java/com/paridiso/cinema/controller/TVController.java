package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.implementation.TVServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/tv")
@RestController
@CrossOrigin(origins = "*")
public class TVController {

    @Autowired
    @Qualifier("TVServiceImpl")
    FilmService filmService;

    @Autowired
    JwtTokenService jwtTokenService;

    @GetMapping(value = "/get/{filmId}")
    public ResponseEntity<?> getTV(@PathVariable String filmId) {
        return ResponseEntity.ok(filmService.getFilm(filmId));
    }

    @GetMapping(value = "/getCustomTV/{filmId}")
    public ResponseEntity<?> getCustomTV(@RequestHeader(value = "Authorization") String jwtToken,
                                            @PathVariable String filmId) {
        return ResponseEntity.ok(filmService.getCustomFilm(filmId, jwtTokenService.getUserIdFromToken(jwtToken)));
    }

    @PostMapping(value = "/trending")
    public ResponseEntity<?> getTVsTrending(@RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesTrending(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/comingSoon")
    public ResponseEntity<?> getTVsComingSoon(@RequestParam Integer pageNo,
                                                 @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesComingSoon(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/playing")
    public ResponseEntity<?> getTVsPlaying(@RequestParam Integer pageNo,
                                              @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesPlaying(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/topRated")
    public ResponseEntity<?> getTopRatedTVs(@RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize){
        return new ResponseEntity<>(filmService.getMoviesTopRated(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/get/topRating")
    public ResponseEntity<?> getTopRatingTVs() {
        return ResponseEntity.ok(filmService.getTopRating());
    }


}
