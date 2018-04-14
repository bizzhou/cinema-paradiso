package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    MovieRepository movieRepository;

    // @TODO: tokenize the keyword
    @Override
    public List<Movie> getMoviesFromKeyword(String keyword) {
        // LinkedHashSet: no duplicates and allow ordering
        Set<Movie> movieSet = new LinkedHashSet<>();
//        movieSet.addAll(getExactMatch(keyword));
        movieSet.addAll(getPhraseMatch(keyword));
        List<Movie> movieList = new ArrayList<>(new LinkedHashSet<Movie>());
        movieList.addAll(movieSet);
        return movieList;
    }

    @Override
    public List<Celebrity> getCelebritiesFromKeyword(String keyword) {
        return null;
    }

    @Override
    public List<TV> getTVsFromKeyword(String keyword) {
        return null;
    }

    private Set<Movie> getExactMatch(String keyword) {
        return movieRepository.findMoviesByTitle(keyword);
    }

    private Set<Movie> getPhraseMatch(String keyword) {
        return movieRepository.findMoviesByTitleContains(keyword);
    }

    private List<String> getBasicWords() {
        List<String> wordsToBeFiltered = new ArrayList<>();
        wordsToBeFiltered.addAll(Arrays.asList("a", "an", "of", "for", "the"));
        return wordsToBeFiltered;
    }

}
