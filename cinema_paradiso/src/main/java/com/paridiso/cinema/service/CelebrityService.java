package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;

import java.util.List;
import java.util.Optional;

public interface CelebrityService {

    List<Celebrity> getCelebrities();

    Celebrity getCelebrity(Integer celebrityId);

    boolean deleteCelebrity(Integer celebrityId);

    boolean updateCelebrity(Celebrity celebrity);

    Optional<Celebrity> addCelebrity(Celebrity celebrity);

}
