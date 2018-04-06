package com.paridiso.cinema.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public interface UtilityService {

    String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException;

    List<String> tokenizedString(String string);
}
