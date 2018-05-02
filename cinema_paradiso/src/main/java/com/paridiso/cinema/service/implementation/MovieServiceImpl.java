package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.constants.MapKeyConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.ListMovieStatus;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.UserRatingRepository;
import com.paridiso.cinema.persistence.UserRepository;
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
    MapKeyConstants mapKeyConstants;

    @Autowired
    UtilityService utilityService;

    @Autowired
    MovieUtility movieUtility;

    @Autowired
    UserRatingRepository userRatingRepository;

    @Autowired
    private UserRepository userRepository;

    private static Logger logger = LogManager.getLogger(MovieServiceImpl.class);

    @Transactional
    @Override
    public Movie addFilm(Film movie) {
//        if (movieRepository.findMovieByImdbId(movie.getImdbId()).get() != null)
//            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieExists());
        return movieRepository.save((Movie) movie);
    }

    @Override
    public Movie getFilm(String filmId) {
        return movieRepository
                .findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
    }

    @Override
    public Movie getCustomFilm(String filmId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        Movie movie = movieRepository
                .findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));

        return setInitialMovieStatus(movie, user);
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
        if (userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie).isPresent()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingExists());
        }
        UserRating userRating = new UserRating();
        userRating.setRatedMovie(movie);
        userRating.setUserRating(rating);
        userRating.setUser(userProfile);
        userRating.setRatedDate(Calendar.getInstance());
        logger.info("old regUserRating " + movie.getRegUserRating());
        Double newRating = calculateNewRating(rating, movie, userProfile.getCritic());
        logger.info("new regUserRating " + newRating);
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
        Double oldRating = calculateOldRating(movie, userRating, userProfile.getCritic());
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
        Double oldRating = calculateOldRating(movie, userRating, userProfile.getCritic());
        movie.setRegUserRating(oldRating);
        Double newRating = calculateNewRating(rating, movie, userProfile.getCritic());
        movie.setRegUserRating(newRating);
        userRating.setUserRating(rating);
        movieRepository.save(movie);
        userRatingRepository.save(userRating);
        return newRating;
    }

    @Transactional
    @Override
    public Movie setInitialMovieStatus(Movie movie, User user) {
        List<Movie> wishListMovies = user.getUserProfile().getWishList().getMovies();
        List<Movie> notInterestedMovies = user.getUserProfile().getNotInterestedList().getMovies();

        if (wishListMovies.stream().anyMatch(m -> m.equals(movie)))
            movie.setListMovieStatus(ListMovieStatus.WISHLIST);
        else if (notInterestedMovies.stream().anyMatch(m -> m.equals(movie)))
            movie.setListMovieStatus(ListMovieStatus.NOT_INTERESTED_LIST);
        else
            movie.setListMovieStatus(ListMovieStatus.NONE);

        return movie;
    }

    private Double calculateOldRating(Movie movie, UserRating userRating, boolean isCritic) {
        Double oldRating = null;
        if (isCritic) {
            oldRating = (movie.getCriticRating() * movie.getNumOfCriticRatings() - userRating.getUserRating()) /
                    (movie.getNumOfCriticRatings() - 1);
            oldRating = Math.round(oldRating * 10.0) / 10.0;
            movie.setNumOfRegUserRatings(movie.getNumOfCriticRatings() - 1);
            movie.setCriticRating(oldRating);
        } else {
            oldRating = (movie.getRegUserRating() * movie.getNumOfRegUserRatings() - userRating.getUserRating()) /
                    (movie.getNumOfRegUserRatings() - 1);
            oldRating = Math.round(oldRating * 10.0) / 10.0;
            movie.setNumOfRegUserRatings(movie.getNumOfRegUserRatings() - 1);
            movie.setRegUserRating(oldRating);
        }
        return oldRating;
    }

    private Double calculateNewRating(Double rating, Movie movie, boolean isCritic) {
        Double tenthPlace = null;
        if (isCritic) {
            Integer oldNumberOfRating = movie.getNumOfCriticRatings();
            Double newRating = (movie.getCriticRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
            movie.setNumOfCriticRatings(oldNumberOfRating + 1);
            tenthPlace = Math.round(newRating * 10.0) / 10.0;
            movie.setCriticRating(tenthPlace);
        } else {
            Integer oldNumberOfRating = movie.getNumOfRegUserRatings();
            Double newRating = (movie.getRegUserRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
            movie.setNumOfRegUserRatings(oldNumberOfRating + 1);
            tenthPlace = Math.round(newRating * 10.0) / 10.0;
            movie.setRegUserRating(tenthPlace);
        }
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
        results.put(mapKeyConstants.getMovieLabel(), moviesPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), moviesPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesComingSoon(Integer pageNo, Integer pageSize) {
        // get 3 week from now
        Calendar daysAfter = movieUtility.getDaysAfterNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();
        // get movies by release date
        Page<Movie> moviesPage = movieRepository.findMoviesByReleaseDateBetween(now, daysAfter, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), moviesPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), moviesPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesTrending(Integer pageNo, Integer pageSize) {
        // get date 1 month before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();
        // get movies with ratings >= 3.5 and released within one month
        Page<Movie> moviesPage = movieRepository.findMoviesByRegUserRatingBetweenAndReleaseDateBetween(
                limitationConstants.getTrendingRating(), limitationConstants.getRatingLimit(),
                daysBeforeNow, now, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), moviesPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), moviesPage.getTotalPages());
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
        results.put(mapKeyConstants.getMovieLabel(), moviePage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), moviePage.getTotalPages());
        return results;
    }

    @Transactional
    @Override
    public HashMap<String, Object> getMoviesTopRated(Integer pageNo, Integer pageSize) {
        Page<Movie> moviePage = movieRepository
                .findTop250ByOrderByWeightedRankDesc(new PageRequest(pageNo, pageSize));

        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), moviePage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), moviePage.getTotalPages());
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

    /**
     * The formula for calculating the Top Rated 250 Titles gives a true Bayesian estimate:

         weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C

         where:
         R = average rating for the movie (mean)
         v = number of ratings for the movie
         m = minimum votes required to be listed in the Top 250 (currently 20)
         C = the mean number of ratings across the whole report
     */
    private void setRatedScores() {
        List<Movie> movies = movieRepository.findAll();
        double v, m, r, weightedRank;
        double c = movieRepository.findAvgNumOfCriticRatings() + movieRepository.findAvgNumOfRegUserRatings();

        for (Movie movie: movies) {
            v = movie.getNumOfCriticRatings() + movie.getNumOfRegUserRatings();
            r = movie.getCriticRating() + movie.getRegUserRating();
            m = limitationConstants.getMinNumOfRatingsForWeightedRank();

            weightedRank = (v / (v + m)) * r + (m / (v + m)) * c;
            movie.setWeightedRank(weightedRank);
            movieRepository.save(movie);
        }
    }

}
