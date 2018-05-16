package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.ReportReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportReviewRepository  extends JpaRepository<ReportReview, String> {
    void deleteAllByReviewReviewId(Long reviewId);
    List<ReportReview> findAllByReviewReviewId(Long reviewId);
}
