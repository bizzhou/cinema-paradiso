package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.ListService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paridiso.cinema.service.WishlistService;
import org.springframework.core.env.Environment;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * You can get the userId from jwt token, no need here.
 */


@RequestMapping("/wishlist")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    @Autowired
    @Qualifier(value = "wishlistServiceImpl")
    ListService listService;

    @Autowired
    JwtTokenService jwtTokenService;

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<WatchList> getWishList(@RequestHeader(value = "Authorization") String jwtToke) {
        return null;
    }

    // http://localhost:8080/wishlist?filmId=1
    @RequestMapping(value = "", method = POST)
    public ResponseEntity<Boolean> addToWishList(@RequestHeader(value = "Authorization") String jwtToken, @RequestParam("filmId") String filmId) {
        Boolean result = listService.addToList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result)
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @RequestMapping(value = "/delete", method = DELETE)
    public ResponseEntity removeFromWishList(@RequestHeader(value = "Authorization") String jwtToken, @RequestParam("filmId") String filmId) {
        listService.removeFromList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/exist", method = DELETE)
    public ResponseEntity<Boolean> isMovieInWishList(@RequestHeader(value = "Authorization") String jwtToken, @RequestParam("filmId") String filmId) {

        Boolean result = listService.isMovieInWishList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);

    }

}
