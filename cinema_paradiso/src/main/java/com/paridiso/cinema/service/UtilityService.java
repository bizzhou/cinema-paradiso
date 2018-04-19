package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;


public interface UtilityService {

    User getUser(Integer id);

    UserProfile getUserProfile(Integer id);

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;

    Collection<? extends Film> shrinkMovieSize(Collection<? extends Film> films);

    Collection<Celebrity> shrinkCelebritySize(Collection<Celebrity> celebrities);
}
