package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Review;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("movie/")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    JwtTokenService jwtTokenService;

    @RequestMapping(value = "/{filmId}/review", method = POST)
    public ResponseEntity addReview(@RequestHeader(value = "Authorization") String jwtToken,
                                             @PathVariable String filmId,
                                             @RequestBody Review review) {
        reviewService.addReview(jwtTokenService.getUserIdFromToken(jwtToken), filmId, review);
        return new ResponseEntity(HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/{filmId}", method = GET, params = "reviewId")
//    public ResponseEntity<Review> getReview(@PathVariable String filmId,
//                                          @RequestParam Long reviewId) {
//
//        Review review = reviewService.getReview(reviewId).orElseThrow(() ->
//                new ResponseStatusException(BAD_REQUEST, "Unable to get review"));
//
//        return new ResponseEntity<>(review, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/{filmId}/review", method = DELETE, params = "reviewId")
//    public ResponseEntity<Review> deleteReview(@RequestHeader(value = "Authorization") String jwtToken,
//                                                @PathVariable String filmId,
//                                                @RequestParam Long reviewId) {
//        Review reviewToBeRemovoed = reviewService.removeReview(jwtTokenService.getUserIdFromToken(jwtToken),filmId, reviewId);
//        if (reviewToBeRemovoed != null)
//            return new ResponseEntity<>(reviewToBeRemovoed, HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @RequestMapping(value = "movie/{movieId}/reviews", method = GET)
    public ResponseEntity<List> getMovieReviews(@PathVariable Long movieId) {
        return null;
    }

    @RequestMapping(value = "user/reviews", method = GET)
    public ResponseEntity<List> getUserReviews() {
        return null;
    }

    @RequestMapping(value = "user/update_review/{reviewID}", method = POST)
    public ResponseEntity<Boolean> updateReview(@PathVariable Integer reviewID,
                                                @RequestParam(value = "review_content", required = true) String reviewContent) {
        return null;
    }

    @RequestMapping(value = "/{filmId}/review", method = POST, params = "liked")
    public ResponseEntity<Boolean> likeReview(@PathVariable Long movieId,
                                              @RequestParam Long reviewId,
                                              @RequestParam Boolean liked) {
        return null;
    }

    @RequestMapping(value = "movie/{movieId}/dislike_review/{reviewId}", method = POST)
    public ResponseEntity<Boolean> dislikeReview(@PathVariable Long movieId,
                                                 @PathVariable Long reviewId) {
        return null;
    }


    @RequestMapping(value = "/", method = GET)
    public String welcome() {
        return "Welcome to cinema paradiso";
    }

}
