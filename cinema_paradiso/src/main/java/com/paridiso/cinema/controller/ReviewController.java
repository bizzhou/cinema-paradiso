package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Review;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("review/")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    JwtTokenService jwtTokenService;

    @RequestMapping(value = "add/{filmId}", method = POST)
    public ResponseEntity addReview(@RequestHeader(value = "Authorization") String jwtToken,
                                    @PathVariable String filmId,
                                    @RequestBody Review review) {
        reviewService.addReview(jwtTokenService.getUserIdFromToken(jwtToken), filmId, review);
        return ResponseEntity.ok(true);
    }


    @PostMapping(value = "edit/{filmId}")
    public ResponseEntity editReview(@RequestHeader(value = "Authorization") String jwtToken,
                                     @RequestBody Review review) {
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        Review review1 = reviewService.editReview(id, review);
        return ResponseEntity.ok(review1);
    }

    @GetMapping(value = "get/user_reviews")
    public ResponseEntity<?> getUserReviews(@RequestHeader(value = "Authorization") String jwtToken) {
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        return ResponseEntity.ok(reviewService.getUserReviews(id));
    }


    @DeleteMapping(value = "delete/{reviewId}")
    public ResponseEntity deleteReview(@RequestHeader(value = "Authorization") String jwtToken,
                                       @PathVariable Long reviewId) {
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        reviewService.removeReview(id, reviewId);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "get/{filmId}")
    public ResponseEntity<List> getMovieReviews(@PathVariable String filmId) {
        return ResponseEntity.ok(reviewService.getMovieReviews(filmId));
    }

    @RequestMapping(value = "/{filmId}/review", method = POST, params = "liked")
    public ResponseEntity<Boolean> likeReview(@PathVariable Long movieId,
                                              @RequestParam Long reviewId,
                                              @RequestParam Boolean liked) {
        return null;
    }

    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "Welcome to cinema paradiso";
    }

}
