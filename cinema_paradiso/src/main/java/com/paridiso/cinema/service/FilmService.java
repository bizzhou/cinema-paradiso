package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;

import javax.swing.text.html.Option;
import java.util.*;

public interface FilmService {

//    Film addFilm(Film film);
    Movie addMovie(Movie movie);

    Movie getMovie(String filmId);

    List<Movie> getMovies();

    void deleteFilm(String filmId);

    void rateFilm(String filmId, Double rating);

    List<Trailer> getTrailers(Long filmId);

    boolean updateTrailer(Long filmId, Integer trailerId);

    Set<Film> getFilmInRage(Calendar startDate, Calendar endDate);

    Set<Film> getSimilarFilm(Long filmId);

    Set<Movie> getMoviesTrending();

    Set<Movie> getMoviesComingSoon();

    Set<Movie> getMoviesPlaying();

    Set<Film> getTopRating();

    Movie updateMovie(Movie movie);
}
