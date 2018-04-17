package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.persistence.CelebrityRepository;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CelebrityRepository celebrityRepository;

    @Autowired

    private static Logger logger = LogManager.getLogger(SearchServiceImpl.class);

    @Override
    public Set<Movie> getMoviesFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        Page<Movie> movies = movieRepository.findMoviesByTitleContains(keyword, new PageRequest(pageNo, pageSize));
        Set<Movie> movieSet = new HashSet<>();
        movieSet.addAll(movies.getContent());
        logger.info(movieSet);
        return movieSet;
    }

    @Override
    public List<Celebrity> getCelebritiesFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        Page<Celebrity> celebrityPages = celebrityRepository
                .findCelebritiesByNameContains(keyword, new PageRequest(pageNo, pageSize));
        List<Celebrity> celebrities = new ArrayList<>(celebrityPages.getContent());
        logger.info(celebrities);
        return celebrities;
    }

    @Override
    public List<TV> getTVsFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        return null;
    }


}
