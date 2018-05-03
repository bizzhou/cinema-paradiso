package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Review;
import com.paridiso.cinema.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    Optional<Review> findReviewByReviewId(Long reviewId);
    Optional<Review> findReviewByMovieAndAuthor(Movie movie, UserProfile author);
}
