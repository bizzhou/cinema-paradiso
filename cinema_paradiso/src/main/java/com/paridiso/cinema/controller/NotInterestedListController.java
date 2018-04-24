package com.paridiso.cinema.controller;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/not-interested")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NotInterestedListController {

    @Autowired
    @Qualifier(value = "notInterestedListServiceImpl")
    ListService listService;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    ExceptionConstants exceptionConstants;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Movie>> getNotInterestedList(@RequestHeader(value = "Authorization") String jwtToken) {
        List<Movie> notInterestedListMovies = listService.getListFromUserId(jwtTokenService.getUserIdFromToken(jwtToken));
        return new ResponseEntity<>(notInterestedListMovies, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<Boolean> addToNotInterestedList(@RequestHeader(value = "Authorization") String jwtToken,
                                                 @RequestParam("filmId") String filmId) {
        Boolean result = listService.addToList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionConstants.getAlreadyInNotInterestedList());
        }
    }

    @DeleteMapping(value = "delete/{filmId}")
    public ResponseEntity removeFromNotInterestedList(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable("filmId") String filmId) {
        listService.removeFromList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        return ResponseEntity.ok(true);
    }

}
