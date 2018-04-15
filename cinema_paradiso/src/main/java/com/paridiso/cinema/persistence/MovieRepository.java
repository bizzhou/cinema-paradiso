package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// JpaRepo can't be <Film, String> because Film is not an entity
public interface MovieRepository extends JpaRepository<Movie, String> {

    Optional<Movie> findMovieByImdbId(String filmImdbId);


    Set<Movie> findMoviesByReleaseDateBetween(Calendar startDate, Calendar currentDate);

    Set<Movie> findMoviesByRatingBetweenAndReleaseDateBetween(Double startRating, Double endRating, Calendar startDate, Calendar endDate);

    Movie findMovieByTitle(String filmTitle);

    List<Movie> findTop50MoviesByTitleContains(String filmTitle);

    Page<Movie> findMoviesByTitleContains(String filmTitle, Pageable pageable);

    Set<Movie> findMoviesByTitleContains(String filmTitle);

    List<Movie> findTop6ByReleaseDateBetweenOrderByBoxOfficeDesc(Calendar startDate, Calendar endDate );


}
