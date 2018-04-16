package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Qualifier(value = "MovieServiceImpl")
public class MovieServiceImpl implements FilmService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    LimitationConstants limitationConstants;

    @Autowired
    UtilityService utilityService;

    @Autowired
    MovieUtility movieUtility;

    @Transactional
    @Override
    public Movie addMovie(Movie movie) {
//        if (movieRepository.findMovieByImdbId(movie.getImdbId()) != null)
//            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieExists());
        return movieRepository.save(movie);
    }

    @Transactional
    @Override
    public Movie getMovie(String filmId) {
        return movieRepository
                .findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist()));
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
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist());
        movieRepository.deleteById(filmId);
    }

    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {
        movieRepository.findMovieByImdbId(movie.getImdbId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist()));
        return movieRepository.save(movie);
    }

    @Transactional
    @Override
    public void rateFilm(String jwtToken, String filmId, Double rating) {
        // add the rating to total rating, then get average
        Movie movie = (Movie) this.getMovie(filmId);
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

    @Transactional
    @Override
    public Set<Movie> getMoviesPlaying() {
        // get 21 days before
        Calendar daysBefore = movieUtility.getDaysBeforeNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        return movieRepository.findMoviesByReleaseDateBetween(daysBefore, now);
    }

    @Transactional
    @Override
    public Set<Movie> getMoviesComingSoon() {
        // get 3 week from now
        Calendar daysAfter = movieUtility.getDaysAfterNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        Collection<Movie> movies =
                utilityService.shrinkMovieSize(movieRepository.findMoviesByReleaseDateBetween(now, daysAfter));

        return (Set<Movie>) movies;
    }

    @Transactional
    @Override
    public Set<Movie> getMoviesTrending() {
        // get date 3 week before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        // get movies with ratings >= 4.0 and released within one week
        Set<Movie> moviesTrending;
        moviesTrending = movieRepository.findMoviesByRatingBetweenAndReleaseDateBetween(
                limitationConstants.getTrendingRating(), limitationConstants.getRatingLimit(),
                daysBeforeNow, now);
        Collection<Movie> movies = utilityService.shrinkMovieSize(moviesTrending);
        // if the number of movies returned above < 6, then find movies rated > 2.5
        if (moviesTrending.size() < limitationConstants.getLeastReturns()) {
            moviesTrending.addAll(movieRepository.findMoviesByRatingBetweenAndReleaseDateBetween(
                    limitationConstants.getAcceptableTrendingRating(), limitationConstants.getRatingLimit(),
                    daysBeforeNow, now));
        }

        return (Set<Movie>) movies;
    }

    @Transactional
    @Override
    public List<Movie> getMoviesTopBoxOffice() {
        // get dates 3 week before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        Collection<Movie> movies = utilityService.shrinkMovieSize(
                movieRepository.findTop6ByReleaseDateBetweenOrderByBoxOfficeDesc(daysBeforeNow, now));
        return (List<Movie>) movies;
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
    public Set<Film> getTopRating() {
        return null;
    }


}
