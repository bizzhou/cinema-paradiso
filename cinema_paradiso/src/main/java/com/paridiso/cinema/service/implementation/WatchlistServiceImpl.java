package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.ListService;

import java.util.List;

public class WatchlistServiceImpl implements ListService {

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public boolean addToList(Integer userId, String filmId) {
        return false;
    }

    @Override
    public List<?> getList() {
        return null;
    }

    @Override
    public boolean removeFromList(Long filmId) {
        return false;
    }

    @Override
    public boolean containsMovie(List<Movie> movies, String filmImdbId) {
        return false;
    }
}
