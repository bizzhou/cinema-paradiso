package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FilmService {

//    Film addFilm(Film film);
    Optional<Movie> addMovie(Movie movie);

    Film getFilm(String filmId);

    List<Movie> getMovies();

    List<Movie> getCarouselMovies();

    void deleteFilm(String filmId);

    void rateFilm(String filmId, Double rating);

    List<Trailer> getTrailers(Long filmId);

    boolean updateTrailer(Long filmId, Integer trailerId);

    List<Film> getFilmInRage(Date startDate, Date endDate);

    List<Film> getSimilarFilm(Long filmId);

    List<Film> getTrending();

    List<Movie> getMoviesPlaying();

    List<Film> getTopRating();

    Movie updateMovie(Movie movie);
}
