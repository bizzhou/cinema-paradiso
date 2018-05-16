package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.WishListRepository;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Qualifier(value = "wishlistServiceImpl")
public class WishlistServiceImpl implements ListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UtilityService utilityService;

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
        Movie movie = utilityService.getMoive(filmImdbId);
        User user = utilityService.getUser(userId);
        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        if (movies.contains(movie) || movies.size() >= limitationConstants.getWishListSize()) {
            return false;
        }
        // add to list
        movies.add(movie);
        user.getUserProfile().getWishList().setMovies(movies);
        wishListRepository.save(user.getUserProfile().getWishList());
        return true;
    }

    @Transactional
    @Override
    public List<Movie> getListFromUserId(Integer userId) {
        User user = utilityService.getUser(userId);
        return user.getUserProfile().getWishList().getMovies();
    }

    @Transactional
    @Override
    public void removeFromList(Integer userId, String filmId) {
        Movie movie = utilityService.getMoive(filmId);
        User user = utilityService.getUser(userId);
        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        movies.remove(movie);
        user.getUserProfile().getWishList().setMovies(movies);
        wishListRepository.save(user.getUserProfile().getWishList());
    }

    @Override
    public Boolean isMovieInList(Integer userId, String filmId) {
        Movie movie = utilityService.getMoive(filmId);
        User user = utilityService.getUser(userId);
        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        return movies.contains(movie);
    }


}
