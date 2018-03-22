package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paridiso.cinema.service.WatchlistService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * You can get the userId from jwt token, no need here.
 */

@RequestMapping("/watchlist")
@RestController
public class WatchlistController {

//    @Autowired
//    WatchlistService wishlistService;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<WatchList> getWatchlist() {
        return null;
    }

    @RequestMapping(value = "/{filmId}", method = POST)
    public ResponseEntity<Boolean> addToWatchList(@PathVariable Integer filmId) {
        return null;
    }

    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> removeFromWatchList(@PathVariable Integer filmId) {
        return null;
    }

}
