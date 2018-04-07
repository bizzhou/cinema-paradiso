package com.paridiso.cinema.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Override
    public String getHashedPassword(String passwordToHash, String salt) {

        String hashedPassword = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(UTF_8));
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes(UTF_8));
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }


            hashedPassword = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("PASSWORD HASHING ALGORITHM DOESN'T EXIST");
        }

        return hashedPassword;
    }

    @Override
    public List<String> tokenizedString(String string) {
        return Arrays.asList(string.split(" "));
    }

    @Override
    public boolean containsMovie(List<Movie> movies, String filmImdbId) {
        for (Movie movie: movies) {
            if (movie.getImdbId().equals(filmImdbId))
                return true;
        }
        return false;
    }

}

