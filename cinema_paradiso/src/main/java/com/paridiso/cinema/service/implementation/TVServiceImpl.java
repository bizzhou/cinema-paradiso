package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.persistence.TvRepository;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
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
    public <T extends Film> T getCustomFilm(String filmId, Integer userId) {
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
    public HashMap<String, Object> getMoviesTrending(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesComingSoon(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesPlaying(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesTopBoxOffice(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesTopRated(Integer pageNo, Integer pageSize) {
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

    @Override
    public Movie setInitialMovieStatus(Movie movie, User user) {
        return null;
    }

    @Override
    public String getFilmId() {
//        String imdbId = tvRepository.findTop1ByOrderByImdbIdDesc().getImdbId();
//        long newId = Long.parseLong(imdbId.replace("tt", "")) + 1;
//        return ("tt" + String.valueOf(newId));
        return null;
    }

    @Override
    public void addFilmography(Movie movie) {
    }
}
