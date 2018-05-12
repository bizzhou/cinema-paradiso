package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.entity.User;
import org.springframework.data.domain.Page;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface FilmService {

    <T extends Film> T addFilm(Film movie);

    <T extends Film> T getFilm(String filmId);

    /**
     * Set whether the movie in Wish List, Not Interested List, or neither
     */
    <T extends Film> T getCustomFilm(String filmId, Integer userId);

    List<? extends Film> getMovies();

    void deleteFilm(String filmId);

    List<Trailer> getTrailers(Long filmId);

    boolean updateTrailer(Long filmId, Integer trailerId);

    Set<Film> getFilmInRage(Calendar startDate, Calendar endDate);

    Set<Film> getSimilarFilm(Long filmId);

    /**
     * Get movies trending
     * Find movies' release date within two weeks && movies rated above 4.5
     *
     * @return a set of qualified movies
     */
    HashMap<String, Object> getMoviesTrending(Integer pageNo, Integer pageSize);

    /**
     * Get movies coming soon
     * Find movies' release date within the following 3 weeks
     *
     * @return a set of qualified movies
     */
    HashMap<String, Object> getMoviesComingSoon(Integer pageNo, Integer pageSize);

    /**
     * Get movies playing now
     * Find movies' release dates in between current date and 3 weeks before
     *
     * @return a set of qualified movies
     */
    HashMap<String, Object> getMoviesPlaying(Integer pageNo, Integer pageSize);

    /**
     * Get movies with the top box office
     * Find movies' release date within 3 weeks and order by box office
     *
     * @return a list of qualified movies
     */
    HashMap<String, Object> getMoviesTopBoxOffice(Integer pageNo, Integer pageSize);

    HashMap<String, Object> getMoviesTopRated(Integer pageNo, Integer pageSize);

    Set<? extends Film> getTopRating();

    Movie updateMovie(Movie movie);

    Double addRating(Integer userId, String filmId, Double rating);

    Double deleteRating(Integer userId, String filmId);

    Double updateRating(Integer userId, String filmId, Double rating);

    Movie setInitialMovieStatus(Movie movie, User user);

    String getFilmId();

    void addFilmography(Movie movie);
}
