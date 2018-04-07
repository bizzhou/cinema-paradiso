package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "MovieServiceImpl")
public class MovieServiceImpl implements FilmService {

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    @Override
    public Optional<Movie> addMovie(Movie movie) {
        if (movieRepository.findMovieByImdbId(movie.getImdbId()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MOVIE EXISTS");
        return Optional.ofNullable(movieRepository.save(movie));
    }

    @Transactional
    @Override
    public Film getFilm(String filmId) {
        return movieRepository.findMovieByImdbId(filmId);
    }

    @Transactional
    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteFilm(String filmId) {
        if (movieRepository.findMovieByImdbId(filmId) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MOVIE DOES NOT EXIST");
        movieRepository.deleteById(filmId);
    }

    @Override
    public void updateFilmRating(Long filmId) {

    }

    @Override
    public List<Trailer> getTrailers(Long filmId) {
        return null;
    }

    @Override
    public boolean updateTrailer(Long filmId, Integer trailerId) {
        return false;
    }

    @Override
    public List<Film> getFilmInRage(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Film> getSimilarFilm(Long filmId) {
        return null;
    }

    @Override
    public List<Film> getTrending() {
        return null;
    }

    @Override
    public List<Film> getPlaying() {
        return null;
    }

    @Override
    public List<Film> getTopRating() {
        return null;
    }

    public List<Movie> getTopBoxOffice() {
        return null;
    }

    @Transactional
    @Override
    public void rateFilm(String filmId, Double rating) {
        // add the rating to total rating, then get average
        Movie movie = (Movie) this.getFilm(filmId);
        movie.setNumberOfRatings(movie.getNumberOfRatings()+1);
        Double newRatings = (movie.getRating() + rating)/movie.getNumberOfRatings();
        movie.setRating(newRatings);
        movieRepository.save(movie);
    }

}
