package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.FilmographyWrapper;

import java.util.List;
import java.util.Optional;

public interface CelebrityService {

    List<Celebrity> getCelebrities();

    Optional<Celebrity> getCelebrity(String celebrityId);

    Boolean deleteCelebrity(Integer celebrityId);

    Boolean updateCelebrity(Celebrity celebrity);

    Optional<Celebrity> addCelebrity(Celebrity celebrity);

    boolean addFilmography(FilmographyWrapper filmography);
}
