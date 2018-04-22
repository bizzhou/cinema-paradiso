package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Review;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.ReviewRepository;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.service.ReviewService;
import com.paridiso.cinema.service.UtilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UtilityService utilityService;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    @Override
    public List<Review> getAudienceReviews(Long filmId) {
        return null;
    }

    @Override
    public List<Review> getCriticReviews(Long filmId) {
        return null;
    }

    @Transactional
    @Override
    public void addReview(Integer userId, String movieId, Review review) {
        Movie movie = utilityService.getMoive(movieId);
        User user = utilityService.getUser(userId);
        if (reviewRepository.findReviewByMovieAndAuthor(movie, user.getUserProfile()).isPresent()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getReviewExists());
        }
        review.setAuthor(user.getUserProfile());
        review.setMovie(movie);
        List<Review> reviews = user.getUserProfile().getReviews() == null
                ? new ArrayList<Review>() : user.getUserProfile().getReviews();
        reviews.add(review);
        logger.info(review.getReviewId());
        user.getUserProfile().setReviews(reviews);
        movie.getReviews().add(review);
        reviewRepository.save(review);
        userProfileRepository.save(user.getUserProfile());
        movieRepository.save(movie);
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getReviewNotFound()));
    }

    @Override
    public Review editReview(Integer userProfileId, Review review) {
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        Review review1 = reviewRepository.findById(review.getReviewId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getReviewNotFound()));
        Review newReview = null;
        // update review in userprofile
        for (Review tempReview : userProfile.getReviews()) {
            if (tempReview.getMovie().getImdbId().equals(review1.getMovie().getImdbId())) {
                tempReview.setReviewContent(review.getReviewContent());
                tempReview.setPostedDate(review.getPostedDate());
                tempReview.setTitle(review.getTitle());
                newReview = review;
            }
        }
        userProfileRepository.save(userProfile);
        return newReview;
    }

    @Override
    public void removeReview(Integer profileId, Long reviewId) {
        logger.info(reviewId);
        Review review = reviewRepository.findReviewByReviewId(reviewId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getReviewNotFound()));
//        logger.error(review);
//        boolean remove = review.getMovie().getReviews().remove(review);
//        movieRepository.save(review.getMovie());
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getMovieReviews(String filmId) {
        Optional<Movie> movieByImdbId = movieRepository.findMovieByImdbId(filmId);
        if (movieByImdbId.isPresent()) {
            return movieByImdbId.get().getReviews();
        } else {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getReviewNotFound());
        }
    }

    @Override
    public boolean detectBadReview(Review review) {
        return false;
    }
}
