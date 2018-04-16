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

import static org.springframework.web.bind.annotation.RequestMethod.*;


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

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<List> getAllMovies() {
        return new ResponseEntity<>(filmService.getMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/{filmId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String filmId) {
        Movie movie = filmService.getMovie(filmId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addMovie(@RequestBody Movie movie) {
        filmService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> deleteMovie(@PathVariable String filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<Boolean> updateMovie(@RequestBody Movie movie) {
        Movie optionalMovie = filmService.updateMovie(movie);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/{filmId}/{rating}", method = POST)
    public ResponseEntity<Boolean> rateMovie(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable String filmId,
                                             @PathVariable Double rating) {
        return null;
//        // add to user
//        boolean result = userService.rateMovie(jwtTokenService.getUserIdFromToken(jwtToken), filmId, rating);
//        if (!result)
//            return ResponseEntity.ok(false);
//
//        // add to film
//        filmService.rateFilm(filmId, rating);
//
//        return ResponseEntity.ok(true);
    }

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
    public ResponseEntity<Set> getMoviesTrending() { return new ResponseEntity<>(filmService.getMoviesTrending(), HttpStatus.OK); }

    @RequestMapping(value = "/comingSoon", method = GET)
    public ResponseEntity<Set> getMoviesComingSoon() {
        return new ResponseEntity<Set>(filmService.getMoviesComingSoon(), HttpStatus.OK);
    }

    @RequestMapping(value = "/playing", method = GET)
    public ResponseEntity<Set> getMoviesPlaying() { return new ResponseEntity<>(filmService.getMoviesPlaying(), HttpStatus.OK); }

    @RequestMapping(value = "/topBoxOffice", method = GET)
    public ResponseEntity<List> getTopBoxOffice() {
        return new ResponseEntity<List>(filmService.getMoviesTopBoxOffice(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}/similar", method = GET)
    public ResponseEntity<Set> getSimilarMovies(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/range", method = GET)
    public ResponseEntity<Set> getMoviesInRange(@RequestParam Calendar startDate, @RequestParam Calendar endDate) {
        return null;
    }


}
