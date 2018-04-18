package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.WishList;
import com.paridiso.cinema.persistence.*;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Qualifier(value = "watchlistServiceImpl")
public class WatchlistServiceImpl implements ListService {
    @Autowired
    WatchListRepository watchListRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UtilityService utilityService;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    LimitationConstants limitationConstants;

    @Autowired
    MovieUtility movieUtility;

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public boolean addToList(Integer userId, String filmId) {
        Movie movie = movieRepository.findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWatchList().getMovies();
        if (movieUtility.containsMovie(movies, filmId) || movies.size() >= limitationConstants.getWatchListSize())
            return false;
        // add to list
        movies.add(movie);
        user.getUserProfile().getWatchList().setMovies(movies);
        watchListRepository.save(user.getUserProfile().getWatchList());
        return true;
    }

    @Override
    public WishList getWishList(Integer userId) {
        return null;
    }

    @Override
    public void removeFromList(Integer userId, String filmId) {

    }

    @Override
    public Boolean isMovieInWishList(Integer userId, String filmId) {
        return null;
    }

}
