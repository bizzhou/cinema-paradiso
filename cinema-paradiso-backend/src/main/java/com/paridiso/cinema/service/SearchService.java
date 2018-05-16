package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<Movie> getMoviesFromKeyword(String keyword, Integer pageNo, Integer pageSize);
    Page<Celebrity> getCelebritiesFromKeyword(String keyword, Integer pageNo, Integer pageSize);
    Page<TV> getTVsFromKeyword(String keyword, Integer pageNo, Integer pageSize);
}
