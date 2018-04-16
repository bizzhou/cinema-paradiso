package com.paridiso.cinema.controller;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.service.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequestMapping("/celebrity")
@RestController
public class CelebrityController {

    @Autowired
    CelebrityService celebrityService;

    @Autowired
    ExceptionConstants exceptionConstants;

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Celebrity>> getCelebrities() {
        return null;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addCelebrity(@RequestBody final Celebrity celebrity) {
        celebrityService.addCelebrity(celebrity)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getCelebritySaveFail()));
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Celebrity> getCelebrity(@PathVariable String id) {
        Celebrity celebrity = celebrityService.getCelebrity(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getCelebrityNotFound()));
        return new ResponseEntity<>(celebrity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id) {
        return null;
    }

    @PostMapping(value = "update/{id{")
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id,
                                                     @RequestBody Celebrity celebrity) {
        return null;
    }

}
