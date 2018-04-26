package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.persistence.TvRepository;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class TVServiceImpl implements FilmService {

    @Autowired
    UtilityService utilityService;

    @Autowired
    TvRepository tvRepository;


    @Override
    public TV addFilm(Film tv) {
        return tvRepository.save((TV)tv);
    }

    @Override
    public TV getFilm(String filmId) {
        return null;
    }

    @Override
    public List<TV> getMovies() {
        return null;
    }

    @Override
    public void deleteFilm(String filmId) {
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
    public Set<Film> getFilmInRage(Calendar startDate, Calendar endDate) {
        return null;
    }

    @Override
    public Set<Film> getSimilarFilm(Long filmId) {
        return null;
    }

    @Override
    public Set<Movie> getMoviesTrending() {
        return null;
    }

    @Override
    public Set<Movie> getMoviesComingSoon() {
        return null;
    }

    @Override
    public Set<Movie> getMoviesPlaying() {
        return null;
    }

    @Override
    public List<Movie> getMoviesTopBoxOffice() {
        return null;
    }

    @Override
    public Set<? extends Film> getTopRating() {
        return null;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Double addRating(Integer userId, String filmId, Double rating) {
        return null;
    }

    @Override
    public Double deleteRating(Integer userId, String filmId) {
        return null;
    }

    @Override
    public Double updateRating(Integer userId, String filmId, Double rating) {
        return null;
    }
}
