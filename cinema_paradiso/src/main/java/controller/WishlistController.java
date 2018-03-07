package controller;

import entity.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.WishlistService;

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

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<WatchList> getWishList() {
        return null;
    }

    @RequestMapping(value = "/{filmId}", method = POST)
    public ResponseEntity<Boolean> addToWishList(@PathVariable Integer filmId) {
        return null;
    }

    @RequestMapping(value = "/{filmId}", method = DELETE)
    public ResponseEntity<Boolean> removeFromWishList(@PathVariable Integer filmId) {
        return null;
    }
}
