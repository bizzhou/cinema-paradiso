package com.paridiso.cinema.persistence;


import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {
    Optional<List<UserRating>> findUserRatingsByRatedMovie(Movie movie);
    Optional<List<UserRating>> findUserRatingsByUser(UserProfile userProfile);
    Optional<UserRating> findUserRatingsByUserAndRatedMovie(UserProfile userProfile, Movie movie);
}
