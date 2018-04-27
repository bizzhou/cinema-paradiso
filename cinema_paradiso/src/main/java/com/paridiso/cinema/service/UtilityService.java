package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;


public interface UtilityService {

    Movie getMoive(String movieId);

    User getUser(Integer id);

    UserProfile getUserProfile(Integer id);

    Celebrity getCelebrity(String id);

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;

    Collection<? extends Film> shrinkMovieSize(Collection<? extends Film> films);

    Collection<Celebrity> shrinkCelebritySize(Collection<Celebrity> celebrities);
}
