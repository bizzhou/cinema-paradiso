package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.entity.WishList;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/wishlist")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    @Autowired
    @Qualifier(value = "wishlistServiceImpl")
    ListService listService;

    @Autowired
    JwtTokenService jwtTokenService;

//    @RequestMapping(value = "/get", method = GET)
    @GetMapping(value = "/get")
    public ResponseEntity<List<Movie>> getWishList(@RequestHeader(value = "Authorization") String jwtToken) {
        List<Movie> wishListMovies = listService.getListFromUserId(jwtTokenService.getUserIdFromToken(jwtToken));
        return new ResponseEntity<>(wishListMovies, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<Boolean> addToWishList(@RequestHeader(value = "Authorization") String jwtToken,
                                                 @RequestParam("filmId") String filmId) {
        Boolean result = listService.addToList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "delete/{filmId}")
    public ResponseEntity removeFromWishList(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable("filmId") String filmId) {
        listService.removeFromList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /**
     * if movie exists in the user's wish list
     * @param jwtToken
     * @param filmId
     * @return
     */
    @RequestMapping(value = "/exist", method = DELETE)
    public ResponseEntity<Boolean> isMovieInWishList(@RequestHeader(value = "Authorization") String jwtToken,
                                                     @RequestParam("filmId") String filmId) {
        Boolean result = listService.isMovieInWishList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);

    }

}
