package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Optional<Movie> findMovieByImdbId(String filmImdbId);

//    Set<Movie> findMoviesByReleaseDateBetween(Calendar startDate, Calendar currentDate);

    Page<Movie> findMoviesByReleaseDateBetween(Calendar startDate, Calendar currentDate, Pageable page);

    Page<Movie> findMoviesByRegUserRatingBetweenAndReleaseDateBetween(Double startRating, Double endRating, Calendar startDate, Calendar endDate, Pageable page);

    Page<Movie> findMoviesByTitleContains(String filmTitle, Pageable pageable);

    Page<Movie> findMoviesByReleaseDateBetweenOrderByBoxOfficeDesc(Calendar startDate, Calendar endDate, Pageable page);

    Set<Movie> findTop50ByOrderByNumOfRegUserRatingsDescRegUserRatingDesc();

    @Query(value = "SELECT AVG(m.numOfRegUserRatings) FROM Movie m")
    Double findAvgNumOfRegUserRatings();

    @Query(value = "SELECT AVG(m.numOfCriticRatings) FROM Movie m")
    Double findAvgNumOfCriticRatings();

    Page<Movie> findTop20ByOrderByWeightedRankDesc(Pageable page);


}
