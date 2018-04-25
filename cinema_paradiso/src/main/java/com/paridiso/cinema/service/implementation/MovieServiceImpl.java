package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.UserRatingRepository;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.utility.MovieUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    @Autowired
    UserRatingRepository userRatingRepository;

    private static Logger logger = LogManager.getLogger(MovieServiceImpl.class);

    @Transactional
    @Override
    public Movie addMovie(Movie movie) {
//        if (movieRepository.findMovieByImdbId(movie.getImdbId()).get() != null)
//            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieExists());
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(String filmId) {
        return movieRepository
                .findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
    }

    @Transactional
    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteFilm(String filmId) {
        if (movieRepository.findMovieByImdbId(filmId).get() == null)
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist());
        movieRepository.deleteById(filmId);
    }

    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {
        movieRepository.findMovieByImdbId(movie.getImdbId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
        return movieRepository.save(movie);
    }

    @Transactional
    @Override
    public Double addRating(Integer userProfileId, String filmId, Double rating) {
        Movie movie = getMovie(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        if (userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie).isPresent()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingExists());
        }
        UserRating userRating = new UserRating();
        userRating.setRatedMovie(movie);
        userRating.setUserRating(rating);
        userRating.setUser(userProfile);
        userRating.setRatedDate(Calendar.getInstance());
        logger.info("old regUserRating " + movie.getRegUserRating());
        Double newRating = calculateNewRating(rating, movie);
        logger.info("new regUserRating " + newRating);
        userRatingRepository.save(userRating);
        movieRepository.save(movie);
        return newRating;
    }


    @Transactional
    @Override
    public Double deleteRating(Integer userProfileId, String filmId) {
        Movie movie = getMovie(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserRating userRating = userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));
        Double oldRating = calculateOldRating(movie, userRating);
        userRatingRepository.delete(userRating);
        return oldRating;
    }

    @Transactional
    @Override
    public Double updateRating(Integer userProfileId, String filmId, Double rating) {
        Movie movie = getMovie(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserRating userRating = userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));
        Double oldRating = calculateOldRating(movie, userRating);
        movie.setRegUserRating(oldRating);
        Double newRating = calculateNewRating(rating, movie);
        movie.setRegUserRating(newRating);
        userRating.setUserRating(rating);
        movieRepository.save(movie);
        userRatingRepository.save(userRating);
        return newRating;
    }

    private Double calculateOldRating(Movie movie, UserRating userRating) {
        Double oldRating = (movie.getRegUserRating() * movie.getNumOfRegUserRatings() - userRating.getUserRating()) /
                (movie.getNumOfRegUserRatings() - 1);
        movie.setRegUserRating(oldRating);
        movie.setNumOfRegUserRatings(movie.getNumOfRegUserRatings() - 1);
        return oldRating;
    }

    private Double calculateNewRating(Double rating, Movie movie) {
        Integer oldNumberOfRating = movie.getNumOfRegUserRatings();
        Double newRating = (movie.getRegUserRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
        movie.setNumOfRegUserRatings(oldNumberOfRating + 1);
        double tenthPlace = Math.round(newRating * 10.0) / 10.0;
        movie.setRegUserRating(tenthPlace);
        return tenthPlace;
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
    public HashMap<String, Object> getMoviesPlaying(Integer pageNo, Integer pageSize) {
        // get 31 days before
        Calendar daysBefore = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        Page<Movie> moviesPage = movieRepository.findMoviesByReleaseDateBetween(daysBefore, now, new PageRequest(pageNo, pageSize));

        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", moviesPage.getContent());
        results.put("movie_page", moviesPage.getTotalPages());

        return results;
    }

    @Override
    public HashMap<String, Object>  getMoviesComingSoon(Integer pageNo, Integer pageSize) {
        // get 3 week from now
        Calendar daysAfter = movieUtility.getDaysAfterNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();

        // get movies by release date
        Page<Movie> moviesPage = movieRepository.findMoviesByReleaseDateBetween(now, daysAfter, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", moviesPage.getContent());
        results.put("movie_page", moviesPage.getTotalPages());

        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesTrending(Integer pageNo, Integer pageSize) {
        // get date 1 month before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();

        // get movies with ratings >= 3.5 and released within one month
        Page<Movie> moviesPage  = movieRepository.findMoviesByRegUserRatingBetweenAndReleaseDateBetween(
                limitationConstants.getTrendingRating(), limitationConstants.getRatingLimit(),
                daysBeforeNow, now, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", moviesPage.getContent());
        results.put("movie_page", moviesPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesTopBoxOffice(Integer pageNo, Integer pageSize) {
        // get dates 3 week before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();

        Page<Movie> moviePage = movieRepository
                .findMoviesByReleaseDateBetweenOrderByBoxOfficeDesc(
                        daysBeforeNow, now, new PageRequest(pageNo, pageSize));

        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", moviePage.getContent());
        results.put("movie_page", moviePage.getTotalPages());

        return results;
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
    public Set<? extends Film> getTopRating() {
        // TODO Generate proper number of regUserRating in the database, not just random....
        Set<Movie> top50ByRatingOrderByRating = movieRepository.findTop50ByOrderByNumOfRegUserRatingsDescRegUserRatingDesc();
        logger.info(top50ByRatingOrderByRating);
        return top50ByRatingOrderByRating;
    }

}
