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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/wishlist")
@RestController
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    @Qualifier(value = "wishlistServiceImpl")
    ListService listService;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    ExceptionConstants exceptionConstants;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Movie>> getWishList(@RequestHeader(value = "Authorization") String jwtToken) {
        List<Movie> wishListMovies = listService.getListFromUserId(jwtTokenService.getUserIdFromToken(jwtToken));
        return new ResponseEntity<>(wishListMovies, HttpStatus.OK);
    }

    /**
     * @exception ResponseStatusException CONFLICT: movie already exists
     * @exception ResponseStatusException BAD_REQUEST: user not logged in
     */
    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity<Boolean> addToWishList(@RequestHeader(value = "Authorization") String jwtToken,
                                                 @RequestParam("filmId") String filmId) {
        Boolean result = listService.addToList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exceptionConstants.getAlreadyInWishList());
        }
    }

    @DeleteMapping(value = "delete/{filmId}")
    public ResponseEntity removeFromWishList(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable("filmId") String filmId) {
        listService.removeFromList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        return ResponseEntity.ok(true);
    }

    /**
     * if movie exists in the user's wish list
     * @param jwtToken
     * @param filmId
     * @return
     */
    @RequestMapping(value = "/exist", method = DELETE)
    public ResponseEntity<Boolean> isMovieInList(@RequestHeader(value = "Authorization") String jwtToken,
                                                     @RequestParam("filmId") String filmId) {
        Boolean result = listService.isMovieInList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);

    }

}
