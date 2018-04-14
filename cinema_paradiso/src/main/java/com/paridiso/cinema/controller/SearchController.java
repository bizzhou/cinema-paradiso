package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
        Set<Movie> movieList = searchService.getMoviesFromKeyword(keyword, pageNo, pageSize);
        return ResponseEntity.ok(movieList);
    }

}
