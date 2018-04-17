package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.TV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvRepository extends JpaRepository<TV, String> {
    Page<TV> findMoviesByTitleContains(String keyword, Pageable pageable);
}
