package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    MovieRepository movieRepository;

    // @TODO: tokenize the keyword
    // @TODO: celebrity search
    @Override
    public List<Movie> getMoviesFromKeyword(String keyword) {
        // LinkedHashSet: no duplicates and allow ordering
        LinkedHashSet<Movie> movieSet = new LinkedHashSet<>();
        movieSet.addAll(getExactMatch(keyword));
        movieSet.addAll(getPhraseMatch(keyword));

        List<Movie> movieList = new ArrayList<>(new LinkedHashSet<Movie>());
        movieList.addAll(movieSet);

        return movieList;
    }

    private List<Movie> getExactMatch(String keyword) {
        return movieRepository.findMoviesByTitle(keyword);
    }

    private List<Movie> getPhraseMatch(String keyword) {
        return movieRepository.findMoviesByTitleContains(keyword);
    }



    private ArrayList<String> getBasicWords() {
        ArrayList wordsToBeFiltered = new ArrayList();
        wordsToBeFiltered.addAll(Arrays.asList("a", "an", "of", "for", "the"));

        return wordsToBeFiltered;
    }

}
