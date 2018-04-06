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
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "MovieServiceImpl")
public class MovieServiceImpl implements FilmService {

    @Autowired
    MovieRepository movieRepository;

//    @Autowired
//    @Qualifier("InputUtilityServiceImpl")
//    UtilityService utilityService;

    public Optional<Movie> addMovie(Movie movie) {
        if (movieRepository.findMovieByImdbId(movie.getImdbId()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MOVIE EXISTS");
        return Optional.ofNullable(movieRepository.save(movie));
    }

    @Override
    public Film getFilm(String filmId) {
        return movieRepository.findMovieByImdbId(filmId);
    }

    @Override
    public Film deleteFilm(Long filmId) {
        return null;
    }

    @Override
    public boolean rateFilm(Long filmId) {
        return false;
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

}
