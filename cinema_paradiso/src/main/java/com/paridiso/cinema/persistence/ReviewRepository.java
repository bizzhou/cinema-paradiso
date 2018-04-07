package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    Optional<Review> findReviewByReviewId(Long reviewId);
}
