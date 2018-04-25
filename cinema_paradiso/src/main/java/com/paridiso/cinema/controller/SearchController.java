package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/search")
@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping
    public ResponseEntity<?> search(@RequestParam String keyword,
                                    @RequestParam Integer pageNo,
                                    @RequestParam Integer pageSize) {
        Page<Movie> movies = searchService.getMoviesFromKeyword(keyword, pageNo, pageSize);
        Page<Celebrity> celebrities = searchService.getCelebritiesFromKeyword(keyword, pageNo, pageSize);
        Page<TV> tvs = searchService.getTVsFromKeyword(keyword, pageNo, pageSize);

        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", movies.getContent());
        results.put("celebrity", celebrities.getContent());
        results.put("tv", tvs.getContent());
        results.put("movie_page", movies.getTotalPages());
        results.put("celebrities_page", celebrities.getTotalPages());
        results.put("tv_page", tvs.getTotalPages());

//        results.put("movie_total", movies.getTotalElements());
//        results.put("celebrities_total", celebrities.getTotalElements());
//        results.put("tv_total", tvs.getTotalElements());

        return ResponseEntity.ok(results);
    }


    @PostMapping(value = "/movie")
    public ResponseEntity<?> searchMoive(@RequestParam String keyword,
                                    @RequestParam Integer pageNo,
                                    @RequestParam Integer pageSize) {
        Page<Movie> movies = searchService.getMoviesFromKeyword(keyword, pageNo, pageSize);
        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", movies.getContent());
        return ResponseEntity.ok(results);
    }

    @PostMapping(value = "/people")
    public ResponseEntity<?> searchPeople(@RequestParam String keyword,
                                         @RequestParam Integer pageNo,
                                         @RequestParam Integer pageSize) {
        Page<Celebrity> celebritiesFromKeyword = searchService.getCelebritiesFromKeyword(keyword, pageNo, pageSize);
        HashMap<String, Object> results = new HashMap<>();
        results.put("celebrity", celebritiesFromKeyword.getContent());
        return ResponseEntity.ok(results);
    }

    @PostMapping(value = "/tv")
    public ResponseEntity<?> searchTV(@RequestParam String keyword,
                                         @RequestParam Integer pageNo,
                                         @RequestParam Integer pageSize) {
        Page<TV> tVsFromKeyword = searchService.getTVsFromKeyword(keyword, pageNo, pageSize);
        HashMap<String, Object> results = new HashMap<>();
        results.put("movie", tVsFromKeyword.getContent());
        return ResponseEntity.ok(results);
    }


}
