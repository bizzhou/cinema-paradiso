package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/celebrity")
@RestController
public class CelebrityController {


    @Autowired
    CelebrityService celebrityService;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<List<Celebrity>> getCelebrities() {
        return null;
    }

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<Boolean> addCelebrity(@RequestBody final Celebrity celebrity) {
        Celebrity optionalCelebrity = celebrityService.addCelebrity(celebrity).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "Unable to add movie"));
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Celebrity> getCelebrity(@PathVariable String id) {
        Celebrity celebrity = celebrityService.getCelebrity(id);
        if (celebrity != null)
            return new ResponseEntity<>(celebrity, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id,
                                                     @RequestBody final Celebrity celebrity) {
        return null;
    }

}
