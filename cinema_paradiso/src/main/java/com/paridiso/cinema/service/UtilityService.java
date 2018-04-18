package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Film;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;


public interface UtilityService {

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;

    Collection<? extends Film> shrinkMovieSize(Collection<? extends Film> films);

    Collection<Celebrity> shrinkCelebritySize(Collection<Celebrity> celebrities);
}
