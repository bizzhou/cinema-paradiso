package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmService {

//    Film addFilm(Film film);
    Movie addMovie(Movie movie);

    Movie getMovie(String filmId);

    List<Movie> getMovies();

    List<Movie> getCarouselMovies();

    void deleteFilm(String filmId);

    void rateFilm(String filmId, Double rating);

    List<Trailer> getTrailers(Long filmId);

    boolean updateTrailer(Long filmId, Integer trailerId);

    Set<Film> getFilmInRage(Date startDate, Date endDate);

    Set<Film> getSimilarFilm(Long filmId);

    List<Film> getTrending();

    Set<Movie> getMoviesComingSoon();

    Set<Movie> getMoviesPlaying();

    Set<Film> getTopRating();

    Movie updateMovie(Movie movie);
}
