package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.persistence.WishListRepository;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.WishlistService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class WishlistServiceImpl implements ListService, WishlistService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public Integer getSize() {
        return null;
    }

    // @Todo: check existence of a movie
    @Transactional
    @Override
    public void addToList(Integer userId, String filmImdbId) {
        Movie movie = movieRepository.findMovieByImdbId(filmImdbId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "USER NOT FOUND"));

        List<Movie> movies = user.getUserProfile().getWishList().getMovies();
        movies.add(movie);

        user.getUserProfile().getWishList().setMovies(movies);

        wishListRepository.save(user.getUserProfile().getWishList());
    }

    @Override
    public List<?> getList() {
        return null;
    }

    @Override
    public boolean removeFromList(Long filmId) {
        return false;
    }

}
