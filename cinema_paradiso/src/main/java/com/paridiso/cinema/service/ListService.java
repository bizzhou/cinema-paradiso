package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.WishList;

import java.util.List;

public interface ListService {

    Integer getSize();

    boolean addToList(Integer userId, String filmId);

    List<Movie> getWishList(Integer userId);

    void removeFromList(Integer userId, String filmId);

    Boolean isMovieInWishList(Integer userId, String filmId);
}
