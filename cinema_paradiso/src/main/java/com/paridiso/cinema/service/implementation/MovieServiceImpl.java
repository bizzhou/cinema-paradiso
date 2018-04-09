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

import java.util.*;

@Service
@Qualifier(value = "MovieServiceImpl")
public class MovieServiceImpl implements FilmService {

    @Autowired
    MovieRepository movieRepository;

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

    // TODO: movies
    @Override
    public List<Movie> getCarouselMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = (Movie) this.getFilm("tt2380307");
        Movie movie2 = (Movie) this.getFilm("tt5052448");
        Movie movie3 = (Movie) this.getFilm("tt1856101");

        movieList.addAll(Arrays.asList(movie1, movie2, movie3));
        return movieList;
    }

    @Transactional
    @Override
    public void deleteFilm(String filmId) {
        if (movieRepository.findMovieByImdbId(filmId) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MOVIE DOES NOT EXIST");
        movieRepository.deleteById(filmId);
    }

    @Transactional
    @Override
    public void rateFilm(String filmId, Double rating) {
        // add the rating to total rating, then get average

        System.out.println(filmId);
        System.out.println(rating);
        Movie movie = (Movie) this.getFilm(filmId);
        if (movie.getNumberOfRatings() == null) {
            movie.setNumberOfRatings(1);
        } else {
            movie.setNumberOfRatings(movie.getNumberOfRatings() + 1);
        }
        Double newRatings = (movie.getRating() + rating) / movie.getNumberOfRatings();
        movie.setRating(newRatings);
        movieRepository.save(movie);
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

    // TODO: find proper movies
    @Transactional
    @Override
    public List<Movie> getMoviesPlaying() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = (Movie) this.getFilm("tt1856101");
        Movie movie2 = (Movie) this.getFilm("tt2380307");
        Movie movie3 = (Movie) this.getFilm("tt5726616");
        Movie movie4 = (Movie) this.getFilm("tt4925292");
        Movie movie5 = (Movie) this.getFilm("tt5052448");
        Movie movie6 = (Movie) this.getFilm("tt5723272");
        movieList.addAll(Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6));
        return movieList;
    }

    @Override
    public List<Film> getTopRating() {
        return null;
    }

    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {
        if (movieRepository.findMovieByImdbId(movie.getImdbId()) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MOVIE DOES NOT EXIST");
        return movieRepository.save(movie);
    }

    public List<Movie> getTopBoxOffice() {
        return null;
    }

}
