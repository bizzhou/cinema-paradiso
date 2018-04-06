package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * You can get the userId from jwt token, no need here.
 */

@RequestMapping("/watchlist")
@RestController
public class WatchlistController {

    @Autowired
    @Qualifier(value = "watchlistServiceImpl")
    ListService listService;

    @Autowired
    JwtTokenService jwtTokenService;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<WatchList> getWatchlist() {
        return null;
    }

    @RequestMapping(value = "", method = POST)
    public ResponseEntity<Boolean> addToWishList(@RequestHeader(value = "Authorization") String jwtToken, @RequestParam("filmId") String filmId) {
        Boolean result = listService.addToList(jwtTokenService.getUserIdFromToken(jwtToken), filmId);
        if (result)
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }
    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> removeFromWatchList(@PathVariable Integer filmId) {
        return null;
    }

}
