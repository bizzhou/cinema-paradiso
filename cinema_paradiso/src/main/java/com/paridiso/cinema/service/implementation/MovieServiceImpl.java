package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
    public List<Movie> getMovies() { return movieRepository.findAll(); }

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
    public void rateFilm(String filmId, Double rating) {
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

    // TODO: movies
    @Override
    public List<Movie> getCarouselMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = (Movie) this.getMovie("tt2380307");
        Movie movie2 = (Movie) this.getMovie("tt5052448");
        Movie movie3 = (Movie) this.getMovie("tt1856101");
        movieList.addAll(Arrays.asList(movie1, movie2, movie3));
        return movieList;
    }

    @Override
    public List<Trailer> getTrailers(Long filmId) {
        return null;
    }

    @Override
    public boolean updateTrailer(Long filmId, Integer trailerId) {
        return false;
    }

    /**
     * Get movies playing now
     * Find movies' release dates in between current date and one week before
     * @return a list of qualified movies
     */
    @Transactional
    @Override
    public Set<Movie> getMoviesPlaying() {
        // get 7 days before
        Calendar oneWeekBefore = movieUtility.getOneWeekBefore();
        Calendar now = movieUtility.getNow();

        // get movies by release date
        return movieRepository.findMoviesByReleaseDateBetween(oneWeekBefore, now);
    }

    /**
     * Get movies coming soon
     * Find movies' release date within the following week
     * @return a list of qualified movies
     */
    @Transactional
    @Override
    public Set<Movie> getMoviesComingSoon() {
        // get one week from now
        Calendar oneWeekAfter = movieUtility.getOneWeekAfter();
        Calendar now = movieUtility.getNow();

        // get movies by release date
        return movieRepository.findMoviesByReleaseDateBetween(now, oneWeekAfter);
    }

    /**
     * Get movies trending
     * Find movies' release date within one week && movies rated above 4.5
     * @return a list of qualified movies
     */
    @Override
    public Set<Movie> getMoviesTrending() {
        // get the date one week before
        Calendar oneWeeokBefore = movieUtility.getOneWeekBefore();
        Calendar now = movieUtility.getNow();

        // get movies with ratings >= 4.5 and released within one week
        Set<Movie> moviesTrending;
        moviesTrending = movieRepository.findMoviesByRatingBetweenAndReleaseDateBetween(
                        limitationConstants.getTrendingRating(), limitationConstants.getRatingLimit(),
                        oneWeeokBefore, now);

        // if the number of movies returned above < 6, then find movies rated > 4.0
        if (moviesTrending.size() < limitationConstants.getLeastReturns()) {
            moviesTrending.addAll(movieRepository.findMoviesByRatingBetweenAndReleaseDateBetween(
                    limitationConstants.getRatingLimit(), limitationConstants.getAcceptableTrendingRating(),
                    oneWeeokBefore, now));
        }

        return moviesTrending;
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
