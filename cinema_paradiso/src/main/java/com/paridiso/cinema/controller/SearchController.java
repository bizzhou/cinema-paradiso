package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RequestMapping("/search")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping
    public ResponseEntity<?> search(@RequestParam String keyword,
                                    @RequestParam Integer pageNo,
                                    @RequestParam Integer pageSize) {
        Set<Movie> movies = searchService.getMoviesFromKeyword(keyword, pageNo, pageSize);
        List<Celebrity> celebrities = searchService.getCelebritiesFromKeyword(keyword, pageNo, pageSize);
        List<TV> tvs = searchService.getTVsFromKeyword(keyword, pageNo, pageSize);
        HashMap<String, Collection> results = new HashMap<>();
        results.put("movie", movies);
        results.put("celebrity", celebrities);
        results.put("tv", tvs);
        return ResponseEntity.ok(results);
    }

}
