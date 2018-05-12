package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTVRatingRepository extends JpaRepository<UserTvRating, Integer> {
    Optional<List<UserTvRating>> findUserTvRatingsByRatedTv(TV tv);
    Optional<List<UserTvRating>> findUserTvRatingsByUser(UserProfile userProfile);
    Optional<UserTvRating> findUserTvRatingsByUserAndRatedTv(UserProfile userProfile, TV tv);

}
