package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.UserService;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paridiso.cinema.service.FilmService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<List> getAllMovies() {
        return ResponseEntity.ok(filmService.getMovies());
    }

    @RequestMapping(value = "/carousel", method = GET)
    public ResponseEntity<List<Movie>> getCarousel() {
        System.out.println("Movie Controller: Get carousel ... ");

        return ResponseEntity.ok(filmService.getCarouselMovies());
    }

    @RequestMapping(value = "/{filmId}", method = GET)
    public ResponseEntity<Movie> getMovie(@PathVariable String filmId) {
        Movie movie = (Movie) filmService.getFilm(filmId);
        if (movie != null)
            return new ResponseEntity<>(movie, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<Boolean> addMovie(@RequestBody Movie movie) {
        Movie optionalMovie = filmService.addMovie(movie).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "Unable to add movie"));
        return ResponseEntity.ok(true);
    }


    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> deleteMovie(@PathVariable String filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{filmId}/{rating}", method = POST)
    public ResponseEntity<Boolean> rateMovie(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable String filmId,
                                             @PathVariable Double rating) {

        System.out.println(jwtToken);
        System.out.println(filmId);
        System.out.println(rating);
        // add to user
        boolean result = userService.rateMovie(jwtTokenService.getUserIdFromToken(jwtToken), filmId, rating);
        if (!result)
            return ResponseEntity.ok(false);

        // add to film
        filmService.rateFilm(filmId, rating);

        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<Boolean> updateMovie(@RequestBody Movie movie) {
        Movie optionalMovie = filmService.updateMovie(movie);
        return ResponseEntity.ok(true);
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
    public ResponseEntity<List> getMoviesTrending() {
        return null;
    }

    @RequestMapping(value = "/playing", method = GET)
    public ResponseEntity<List> getMoviesPlaying() {
        return new ResponseEntity<>(filmService.getMoviesPlaying(), HttpStatus.OK);
    }

    @RequestMapping(value = "/top_boxoffice", method = GET)
    public ResponseEntity<List> getTopBoxOffice() {
        return null;
    }


    @RequestMapping(value = "/{id}/similar", method = GET)
    public ResponseEntity<List> getSimilarMovies(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/range", method = GET)
    public ResponseEntity<List> getMoviesInRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return null;
    }


}
