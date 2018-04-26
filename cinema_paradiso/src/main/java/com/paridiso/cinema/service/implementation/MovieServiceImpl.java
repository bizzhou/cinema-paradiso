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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public Movie addFilm(Film movie) {
//        if (movieRepository.findMovieByImdbId(movie.getImdbId()).get() != null)
//            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieExists());
        return movieRepository.save((Movie)movie);
    }

    @Override
    public Movie getFilm(String filmId) {
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
        Movie movie = getFilm(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        if (userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie).isPresent() == true) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingExists());
        }
        UserRating userRating = new UserRating();
        userRating.setRatedMovie(movie);
        userRating.setUserRating(rating);
        userRating.setUser(userProfile);
        userRating.setRatedDate(Calendar.getInstance());
        logger.info("old rating " + movie.getRating());
        Double newRating = calculateNewRating(rating, movie);
        logger.info("new rating " + newRating);
        userRatingRepository.save(userRating);
        movieRepository.save(movie);
        return newRating;
    }


    @Transactional
    @Override
    public Double deleteRating(Integer userProfileId, String filmId) {
        Movie movie = getFilm(filmId);
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
        Movie movie = getFilm(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserRating userRating = userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));
        Double oldRating = calculateOldRating(movie, userRating);
        movie.setRating(oldRating);
        Double newRating = calculateNewRating(rating, movie);
        movie.setRating(newRating);
        userRating.setUserRating(rating);
        movieRepository.save(movie);
        userRatingRepository.save(userRating);
        return newRating;
    }

    private Double calculateOldRating(Movie movie, UserRating userRating) {
        Double oldRating = (movie.getRating() * movie.getNumberOfRatings() - userRating.getUserRating()) /
                (movie.getNumberOfRatings() - 1);
        movie.setRating(oldRating);
        movie.setNumberOfRatings(movie.getNumberOfRatings() - 1);
        return oldRating;
    }

    private Double calculateNewRating(Double rating, Movie movie) {
        Integer oldNumberOfRating = movie.getNumberOfRatings();
        Double newRating = (movie.getRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
        movie.setNumberOfRatings(oldNumberOfRating + 1);
        double tenthPlace = Math.round(newRating * 10.0) / 10.0;
        movie.setRating(tenthPlace);
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
    public Set<Movie> getMoviesPlaying() {
        // get 21 days before
        Calendar daysBefore = movieUtility.getDaysBeforeNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        return movieRepository.findMoviesByReleaseDateBetween(daysBefore, now);
    }

    @Override
    public Set<Movie> getMoviesComingSoon() {
        // get 3 week from now
        Calendar daysAfter = movieUtility.getDaysAfterNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        Collection<? extends Film> films =
                utilityService.shrinkMovieSize(movieRepository.findMoviesByReleaseDateBetween(now, daysAfter));
        return (Set<Movie>) films;
    }

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
        Collection<? extends Film> films = utilityService.shrinkMovieSize(moviesTrending);
        // if the number of movies returned above < 6, then find movies rated > 2.5
        if (moviesTrending.size() < limitationConstants.getLeastReturns()) {
            moviesTrending.addAll(movieRepository.findMoviesByRatingBetweenAndReleaseDateBetween(
                    limitationConstants.getAcceptableTrendingRating(), limitationConstants.getRatingLimit(),
                    daysBeforeNow, now));
        }
        return (Set<Movie>) films;
    }

    @Override
    public List<Movie> getMoviesTopBoxOffice() {
        // get dates 3 week before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getThreeWeeksRange());
        Calendar now = movieUtility.getNow();
        Collection<? extends Film> films = utilityService.shrinkMovieSize(
                movieRepository.findTop6ByReleaseDateBetweenOrderByBoxOfficeDesc(daysBeforeNow, now));
        return (List<Movie>) films;
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
        // TODO Generate proper number of rating in the database, not just random....
        Set<Movie> top50ByRatingOrderByRating = movieRepository.findTop50ByOrderByNumberOfRatingsDescRatingDesc();
        logger.info(top50ByRatingOrderByRating);
        return top50ByRatingOrderByRating;
    }

}
