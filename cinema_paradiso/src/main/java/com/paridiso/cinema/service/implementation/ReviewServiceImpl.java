package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.*;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.ReviewService;
import com.paridiso.cinema.service.UtilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    @Qualifier("MovieServiceImpl")
    FilmService filmService;

    @Autowired
    UtilityService utilityService;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    UserRatingRepository userRatingRepository;

    @Autowired
    ReportReviewRepository reportReviewRepository;

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
    public Review addReview(Integer userId, String movieId, Review review) {
        Movie movie = utilityService.getMoive(movieId);
        User user = utilityService.getUser(userId);
        if (user.getRole() == Role.ROLE_ADMIN) {
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getAdminCannotReview());
        }
        if (reviewRepository.findReviewByMovieAndAuthor(movie, user.getUserProfile()).isPresent()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getReviewExists());
        }

        review.setAuthor(user.getUserProfile());
        review.setPostedDate(Calendar.getInstance());
        review.setMovie(movie);
        review.setCriticReview(user.getUserProfile().getCritic());
        review.setUserRating(this.getRating(movieId, user.getUserProfile().getId()));
        return reviewRepository.save(review);
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getReviewNotFound()));
    }

    @Override
    public Review editReview(Integer userProfileId, Review review) {
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        Review review1 = getReview(review.getReviewId());
        Review newReview = null;
        // update review when find same in user profile.
        for (Review tempReview : userProfile.getReviews()) {
            if (tempReview.getMovie().getImdbId().equals(review1.getMovie().getImdbId())) {
                tempReview.setReviewContent(review.getReviewContent());
                tempReview.setPostedDate(Calendar.getInstance());
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
        Review review = getReview(reviewId);
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
    public List<Review> getUserReviews(Integer userProfileId) {
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        return userProfile.getReviews();
    }

    @Override
    public boolean detectBadReview(Review review) {
        return false;
    }

    private Double getRating(String filmId, Integer userProfileId) {
        Movie movie = filmService.getFilm(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserRating userRating = userRatingRepository.findUserRatingsByUserAndRatedMovie(userProfile, movie)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));

        return userRating.getUserRating();
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Transactional
    @Override
    public void reportReview(Integer userProfileId, Long reviewId, String reportReason) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()
                -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getReviewNotFound()));;
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        ReportReview reportedReview = new ReportReview();
        reportedReview.setReview(review);
        reportedReview.setReportBy(userProfile);
        reportedReview.setReportReason(reportReason);
        reportReviewRepository.save(reportedReview);
    }


}
