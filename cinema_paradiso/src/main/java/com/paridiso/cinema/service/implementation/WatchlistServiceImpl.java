package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.*;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
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

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public boolean addToList(Integer userId, String filmId) {
        // find movie
        Movie movie = movieRepository.findMovieByImdbId(filmId);

        // find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "USER NOT FOUND"));

        // check movie existence and size limit
        List<Movie> movies = user.getUserProfile().getWatchList().getMovies();


        if (utilityService.containsMovie(movies, filmId) || movies.size() >= user.getUserProfile().getWatchList().getSizeLimit())
            return false;

        // add to list
        movies.add(movie);
        user.getUserProfile().getWatchList().setMovies(movies);

        watchListRepository.save(user.getUserProfile().getWatchList());
        return true;
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
