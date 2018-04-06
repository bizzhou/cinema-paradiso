package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.persistence.SearchRepository;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    SearchRepository searchRepository;

    // @TODO: tokenize the keyword
    @Override
    public List<Movie> getMoviesFromKeyword(String keyword) {
        // LinkedHashSet: no duplicates and allow ordering
        LinkedHashSet<Movie> moviesSet = new LinkedHashSet<>();
        moviesSet.addAll(getExactMatch(keyword));
        moviesSet.addAll(getPhraseMatch(keyword));

        List<Movie> movieList = new ArrayList<>(new LinkedHashSet<Movie>());
        movieList.addAll(moviesSet);

        return movieList;
    }

    private List<Movie> getExactMatch(String keyword) {
        return searchRepository.findMoviesByTitle(keyword);
    }

    private List<Movie> getPhraseMatch(String keyword) {
        return searchRepository.findMoviesByTitleContains(keyword);
    }



    private ArrayList<String> getBasicWords() {
        ArrayList wordsToBeFiltered = new ArrayList();
        wordsToBeFiltered.addAll(Arrays.asList("a", "an", "of", "for", "the"));

        return wordsToBeFiltered;
    }

}
