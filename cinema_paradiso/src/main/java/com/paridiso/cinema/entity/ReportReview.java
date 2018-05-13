package com.paridiso.cinema.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "ReportReviews", uniqueConstraints = @UniqueConstraint(columnNames = {"reportId"}))
public class ReportReview {
    private Integer reportId;
    private Review review;
    private String reportReason;
    private UserProfile reportBy;

    public ReportReview() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    public Integer getReportId() {
        return reportId;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewId", nullable = false)
    public Review getReview() {
        return review;
    }

    @Type(type = "text")
    public String getReportReason() {
        return reportReason;
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    public UserProfile getReportBy() {
        return reportBy;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public void setReportBy(UserProfile reportBy) {
        this.reportBy = reportBy;
    }
}
