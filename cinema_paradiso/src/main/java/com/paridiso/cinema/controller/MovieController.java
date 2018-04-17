package com.paridiso.cinema.controller;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;


@RequestMapping("/movie")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    @Qualifier("MovieServiceImpl")
    FilmService filmService;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    RegUserServiceImpl userService;

    @Autowired
    ExceptionConstants exceptionConstants;

    private static Logger logger = LogManager.getLogger(MovieController.class);

    @GetMapping(value = "/get/all")
    public ResponseEntity<List> getAllMovies() {
        return ResponseEntity.ok(filmService.getMovies());
    }

    @GetMapping(value = "get/{filmId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String filmId) {
        Movie movie = filmService.getMovie(filmId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addMovie(@RequestBody Movie movie) {
        filmService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{filmId}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable String filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> updateMovie(@RequestBody Movie movie) {
        Movie optionalMovie = filmService.updateMovie(movie);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/rate/{filmId}/{rating}")
    public ResponseEntity<Boolean> rateMovie(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable String filmId,
                                             @PathVariable Double rating) {
        filmService.rateFilm(jwtToken, filmId, rating);
        return null;
    }

    @PostMapping(value = "/update/poster/{id}")
    public ResponseEntity<Boolean> updatePoster(@PathVariable Integer id, @RequestBody String poster) {
        return null;
    }

    @PostMapping(value = "/update/trailer/{id}")
    public ResponseEntity<Boolean> updateTrailer(@PathVariable Integer id, @RequestBody String trailer) {
        return null;
    }

    @GetMapping(value = "/get/trailer/{id}")
    public ResponseEntity<Boolean> getTrailers(@PathVariable Integer id) {
        return null;
    }

    @GetMapping(value = "/get/trending")
    public ResponseEntity<Set> getMoviesTrending() {
        return new ResponseEntity<>(filmService.getMoviesTrending(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/comingSoon")
    public ResponseEntity<Set> getMoviesComingSoon() {
        return new ResponseEntity<Set>(filmService.getMoviesComingSoon(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/playing")
    public ResponseEntity<Set> getMoviesPlaying() {
        return new ResponseEntity<>(filmService.getMoviesPlaying(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/topBoxOffice")
    public ResponseEntity<List> getTopBoxOffice() {
        return new ResponseEntity<List>(filmService.getMoviesTopBoxOffice(), HttpStatus.OK);
    }

    @GetMapping(value = "get/similar/{id}")
    public ResponseEntity<Set> getSimilarMovies(@PathVariable Integer id) {
        return null;
    }

    @GetMapping(value = "/get/range")
    public ResponseEntity<Set> getMoviesInRange(@RequestParam Calendar startDate, @RequestParam Calendar endDate) {
        return null;
    }

    @GetMapping(value = "/get/topRating")
    public ResponseEntity<?> getTopRatingMovies() {
        return ResponseEntity.ok(filmService.getTopRating());
    }

}
