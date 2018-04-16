package com.paridiso.cinema.service;

import java.security.NoSuchAlgorithmException;

public interface UtilityService {

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;

}
