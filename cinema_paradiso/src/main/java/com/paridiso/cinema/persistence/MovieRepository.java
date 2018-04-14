package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
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

    Set<Movie> findMoviesByTitle(String filmTitle);

    Set<Movie> findMoviesByTitleContains(String filmTitle);

}
