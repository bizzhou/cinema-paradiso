package com.paridiso.cinema.controller;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.enumerations.Role;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RequestMapping("/movie")
@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping(value = "/get/{filmId}")
    public ResponseEntity<?> getMovie(@PathVariable String filmId) {
        return ResponseEntity.ok(filmService.getFilm(filmId));
    }

    @GetMapping(value = "/getCustomMovie/{filmId}")
    public ResponseEntity<?> getCustomMovie(@RequestHeader(value = "Authorization") String jwtToken,
                                            @PathVariable String filmId) {
        return ResponseEntity.ok(filmService.getCustomFilm(filmId, jwtTokenService.getUserIdFromToken(jwtToken)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addMovie(@RequestBody Movie movie) {
        filmService.addFilm(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{filmId}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable String filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> updateMovie(@RequestBody Movie movie) {
        filmService.updateMovie(movie);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "add/rating/{filmId}/{rating}")
    public ResponseEntity<?> addRating(@RequestHeader(value = "Authorization") String jwtToken,
                                       @PathVariable String filmId,
                                       @PathVariable Double rating) {
        if (rating > 5 || rating < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionConstants.getInvalidRatingNumber());
        }
        Integer id = jwtTokenService.getUserIdFromToken(jwtToken);
        Double newRating = filmService.addRating(id, filmId, rating);
        return ResponseEntity.ok(newRating);
    }

    @DeleteMapping(value = "delete/rating/{filmId}")
    public ResponseEntity<?> deleteRating(@RequestHeader(value = "Authorization") String jwtToken,
                                          @PathVariable String filmId) {
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        Double newRating = filmService.deleteRating(id, filmId);
        return ResponseEntity.ok(newRating);
    }

    @PostMapping(value = "edit/rating/{filmId}/{rating}")
    public ResponseEntity<?> editRating(@RequestHeader(value = "Authorization") String jwtToken,
                                        @PathVariable String filmId,
                                        @PathVariable Double rating) {
        if (rating > 5 || rating < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionConstants.getInvalidRatingNumber());
        }
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        Double newRating = filmService.updateRating(id, filmId, rating);
        return ResponseEntity.ok(newRating);
    }

    @PostMapping(value = "/trending")
    public ResponseEntity<?> getMoviesTrending(@RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesTrending(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/comingSoon")
    public ResponseEntity<?> getMoviesComingSoon(@RequestParam Integer pageNo,
                                                 @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesComingSoon(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/playing")
    public ResponseEntity<?> getMoviesPlaying(@RequestParam Integer pageNo,
                                              @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesPlaying(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/topBoxOffice")
    public ResponseEntity<?> getTopBoxOffice(@RequestParam Integer pageNo,
                                             @RequestParam Integer pageSize) {
        return new ResponseEntity<>(filmService.getMoviesTopBoxOffice(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/topRated")
    public ResponseEntity<?> getTopRatedMovies(@RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize){
        return new ResponseEntity<>(filmService.getMoviesTopRated(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/get/range")
    public ResponseEntity<Set> getMoviesInRange(@RequestParam Calendar startDate, @RequestParam Calendar endDate) {
        return null;
    }

    @GetMapping(value = "/get/topRating")
    public ResponseEntity<?> getTopRatingMovies() {
        return ResponseEntity.ok(filmService.getTopRating());
    }

    @GetMapping(value = "get/new_film_id")
    public ResponseEntity<String> getNewFilmId() {
        String filmId = filmService.getFilmId();
        return ResponseEntity.ok(filmId);
    }

}
