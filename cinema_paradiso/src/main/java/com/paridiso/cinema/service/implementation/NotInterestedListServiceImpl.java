package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.ListService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(value = "notInterestedListServiceImpl")
public class NotInterestedListServiceImpl implements ListService {

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public boolean addToList(Integer userId, String filmId) {
        return false;
    }

    @Override
    public List<Movie> getListFromUserId(Integer userId) {
        return null;
    }

    @Override
    public void removeFromList(Integer userId, String filmId) {

    }

    @Override
    public Boolean isMovieInList(Integer userId, String filmId) {
        return null;
    }
}
