package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.persistence.CelebrityRepository;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.TvRepository;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CelebrityRepository celebrityRepository;

    @Autowired
    TvRepository tvRepository;

    @Autowired
    UtilityServiceImpl utilityService;

    @Override
    public Page<Movie> getMoviesFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        Page<Movie> moviePage = movieRepository.findMoviesByTitleContains(keyword, new PageRequest(pageNo, pageSize));
        return moviePage;
    }

    @Override
    public Page<Celebrity> getCelebritiesFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        Page<Celebrity> celebrityPage = celebrityRepository
                .findCelebritiesByNameContains(keyword, new PageRequest(pageNo, pageSize));
        return celebrityPage;
    }

    @Override
    public Page<TV> getTVsFromKeyword(String keyword, Integer pageNo, Integer pageSize) {
        Page<TV> tvPage = tvRepository.findMoviesByTitleContains(keyword, new PageRequest(pageNo, pageSize));
        return tvPage;
    }


}
