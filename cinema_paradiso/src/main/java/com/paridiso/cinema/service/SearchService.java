package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Movie;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface SearchService {
    List<Movie> getMoviesFromKeyword(String keyword);
}
