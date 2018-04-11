package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/search")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    @Autowired
    SearchService searchService;

    // http://localhost:8080/search?keyword=the+shape
    @RequestMapping(method = POST)
    public ResponseEntity<List<Movie>> search(@RequestParam("keyword") String keyword) {
        List<Movie> movieList = searchService.getMoviesFromKeyword(keyword);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

}
