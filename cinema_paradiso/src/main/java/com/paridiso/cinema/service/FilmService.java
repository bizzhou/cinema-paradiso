package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Trailer;

import java.util.Date;
import java.util.List;

public interface FilmService {

    boolean addFilm(Film film);

    Film getFilm(Film film);

    Film deleteFilm(Long filmId);

    boolean rateFilm(Long filmId);

    void updateFilmRating(Long filmId);

    List<Trailer> getTrailers(Long filmId);

    boolean updateTrailer(Long filmId, Integer trailerId);

    List<Film> getFilmInRage(Date startDate, Date endDate);

    List<Film> getSimilarFilm(Long filmId);

    List<Film> getTrending();

    List<Film> getPlaying();

    List<Film> getTopRating();

}
