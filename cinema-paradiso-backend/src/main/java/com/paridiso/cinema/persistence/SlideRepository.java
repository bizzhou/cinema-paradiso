package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlideRepository extends JpaRepository<Slide, Integer> {
    Optional<Slide> findSlideBySlideId(Integer slideId);
}
