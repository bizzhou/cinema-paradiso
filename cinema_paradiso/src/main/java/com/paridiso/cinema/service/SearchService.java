package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface SearchService {
    Set<Movie> getMoviesFromKeyword(String keyword, Integer pageNo, Integer pageSize);
    List<Celebrity> getCelebritiesFromKeyword(String keyword);
    List<TV> getTVsFromKeyword(String keyword);
}
