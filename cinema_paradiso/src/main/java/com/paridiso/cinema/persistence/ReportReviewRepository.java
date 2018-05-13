package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.ReportReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportReviewRepository  extends JpaRepository<ReportReview, String> {
}
