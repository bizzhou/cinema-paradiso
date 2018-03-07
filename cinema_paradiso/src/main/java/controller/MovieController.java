package controller;

import entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FilmService;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RequestMapping("/movie")
@RestController
public class MovieController {

    @Autowired
    @Qualifier("MovieServiceImpl")
    FilmService filmService;

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<List> getAllMovies() {
        return null;
    }


    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "/add_movie", method = POST)
    public ResponseEntity<Boolean> addMvoie(@RequestBody Movie movie) {
        return null;
    }


    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Boolean> deleteMovie(@PathVariable Long id) {
        return null;
    }


    @RequestMapping(value = "/rate_movie/{id}", method = POST)
    public ResponseEntity<Boolean> rateMovie(@PathVariable Long id,
                                             @RequestParam(value = "rating", required = true) Double rating) {
        return null;
    }

    @RequestMapping(value = "/update_movie", method = POST)
    public ResponseEntity<Boolean> updateMovie(@RequestBody Movie movie) {
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
    public ResponseEntity<List> getMoviesTrending() {
        return null;
    }

    @RequestMapping(value = "/playing", method = GET)
    public ResponseEntity<List> getMoviesPlaying() {
        return null;
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
