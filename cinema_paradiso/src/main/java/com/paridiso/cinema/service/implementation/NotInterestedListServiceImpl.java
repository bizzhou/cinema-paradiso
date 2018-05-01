package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.*;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Qualifier(value = "notInterestedListServiceImpl")
public class NotInterestedListServiceImpl implements ListService {

    @Autowired
    NotInterestedListRepository notInterestedListRepository;

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
    public boolean addToList(Integer userId, String filmId) {

        Movie movie = movieRepository.findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getNotInterestedList().getMovies();
        if (movieUtility.containsMovie(movies, filmId) || movies.size() >= limitationConstants.getWishListSize()) {
            return false;
        }

        // add to list
        movies.add(movie);
        user.getUserProfile().getNotInterestedList().setMovies(movies);
        notInterestedListRepository.save(user.getUserProfile().getNotInterestedList());
        return true;
    }

    @Override
    public List<Movie> getListFromUserId(Integer userId) {
        return null;
    }

    @Transactional
    @Override
    public void removeFromList(Integer userId, String filmId) {
        Movie movie = movieRepository.findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieNotFound()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getNotInterestedList().getMovies();
        movies.remove(movie);

        user.getUserProfile().getNotInterestedList().setMovies(movies);
        notInterestedListRepository.save(user.getUserProfile().getNotInterestedList());
    }

    @Override
    public Boolean isMovieInList(Integer userId, String filmId) {
        return null;
    }
}
