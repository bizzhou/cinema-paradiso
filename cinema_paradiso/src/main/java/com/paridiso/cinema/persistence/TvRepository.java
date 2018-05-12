package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.TV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.Optional;

public interface TVRepository extends JpaRepository<TV, String> {

    Page<TV> findMoviesByTitleContains(String keyword, Pageable pageable);

    Page<TV> findTVSByReleaseDateBetween(Calendar startDate, Calendar currentDate, Pageable page);

    Page<TV> findTVSByRegUserRatingBetweenAndReleaseDateBetween(Double startRating, Double endRating, Calendar startDate, Calendar endDate, Pageable page);

    Page<TV> findTop20ByOrderByWeightedRankDesc(Pageable page);

    TV findTop1ByOrderByImdbIdDesc();

    Optional<TV> findTVByImdbId(String imdbId);

    @Query(value = "SELECT AVG(m.numOfRegUserRatings) FROM TV m")
    Double findAvgNumOfRegUserRatings();

    @Query(value = "SELECT AVG(m.numOfCriticRatings) FROM TV m")
    Double findAvgNumOfCriticRatings();

    @Query(value = "SELECT AVG(m.regUserRating) FROM TV m")
    Double findAvgRegUserRatings();

    @Query(value = "SELECT AVG(m.criticRating) FROM TV m")
    Double findAvgCriticRatings();


}
