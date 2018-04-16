package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Movie;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;


public interface UtilityService {

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;
    Collection<Movie> shrinkMovieSize(Collection<Movie> movies);

}
