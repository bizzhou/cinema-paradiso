package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.persistence.WishListRepository;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.service.WishlistService;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Qualifier(value = "wishlistServiceImpl")
public class WishlistServiceImpl implements ListService, WishlistService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UtilityService utilityService;

    @Autowired
    MovieUtility movieUtility;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    LimitationConstants limitationConstants;

    @Override
    public Integer getSize() {
        return null;
    }

    @Transactional
    @Override
    public boolean addToList(Integer userId, String filmImdbId) {
        Movie movie = movieRepository.findMovieByImdbId(filmImdbId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        if (movieUtility.containsMovie(movies, filmImdbId) || movies.size() >= limitationConstants.getWishListSize()) {
            return false;
        }

        // add to list
        movies.add(movie);
        user.getUserProfile().getWishList().setMovies(movies);
        wishListRepository.save(user.getUserProfile().getWishList());
        return true;
    }

    @Override
    public List<Movie> getWishList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
        return user.getUserProfile().getWishList().getMovies();
    }

    @Override
    public void removeFromList(Integer userId, String filmId) {
        // find movie
        Movie movie = movieRepository.findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieNotFound()));

        // find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        for (Movie m : movies) {
            if (m.getImdbId().equals(movie.getImdbId())) {
                movies.remove(m);
                break;
            }
        }

        user.getUserProfile().getWishList().setMovies(movies);
        wishListRepository.save(user.getUserProfile().getWishList());
    }

    @Override
    public Boolean isMovieInWishList(Integer userId, String filmId) {

        // find movie
        Movie movie = movieRepository.findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieNotFound()));

        // find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        return movieUtility.containsMovie(movies, filmId);
    }


}
