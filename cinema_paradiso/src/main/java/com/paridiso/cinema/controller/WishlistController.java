package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    ListService listService;

    @Autowired
    private JwtTokenGenerator generator;

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    private Environment environment;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<WatchList> getWishList() {
        return null;
    }

    // http://localhost:8080/wishlist?filmId=1
    @RequestMapping(value = "", method = POST)
    public ResponseEntity<Boolean> addToWishList(@RequestHeader(value = "Authorization") String jwtToken, @RequestParam("filmId") String filmId) {

        ObjectNode objectNode = objectMapper.createObjectNode();
        int headerLength = environment.getProperty("token.type").length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));
        listService.addToList(validatedUser.getUserID(), filmId);

        return null;
    }

    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> removeFromWishList(@PathVariable Integer filmId) {
        return null;
    }
}
