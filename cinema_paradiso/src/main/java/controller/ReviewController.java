package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReviewService;
import service.UtilityService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    @Qualifier("InputUtilityServiceImpl")
    private UtilityService utilityService;

    @RequestMapping(value = "movie/{movieId}/review/add", method = POST)
    public ResponseEntity<Boolean> addReview(@PathVariable Long movieId,
                                             @RequestParam(value = "review_content", required = true) String reviewContent) {
        return null;
    }

    @RequestMapping(value = "movie/{movieId}/review/{id}", method = DELETE)
    public ResponseEntity<Boolean> deleteReview(@PathVariable Long movieId, @PathVariable Integer id) {
        return null;
    }

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

    @RequestMapping(value = "movie/{movieId}/like_reviews/{reviewId}", method = POST)
    public ResponseEntity<Boolean> likeReview(@PathVariable Long movieId,
                                              @PathVariable Long reviewId) {
        return null;
    }

    @RequestMapping(value = "movie/{movieId}/dislike_review/{reviewId}", method = POST)
    public ResponseEntity<Boolean> dislikeReview(@PathVariable Long movieId,
                                                 @PathVariable Long reviewId) {
        return null;
    }

}
