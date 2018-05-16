package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Movie;

import java.util.List;

public interface ListService {

    Integer getSize();

    boolean addToList(Integer userId, String filmId);

    List<Movie> getListFromUserId(Integer userId);

    void removeFromList(Integer userId, String filmId);

    Boolean isMovieInList(Integer userId, String filmId);


}