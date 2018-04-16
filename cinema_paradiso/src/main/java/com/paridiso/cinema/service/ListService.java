package com.paridiso.cinema.service;

import java.util.List;

public interface ListService {

    Integer getSize();

    boolean addToList(Integer userId, String filmId);

    List<?> getList();

    void removeFromList(Integer userId, String filmId);

    Boolean isMovieInWishList(Integer userId, String filmId);
}
