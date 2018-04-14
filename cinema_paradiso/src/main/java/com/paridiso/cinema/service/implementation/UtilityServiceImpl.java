package com.paridiso.cinema.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.constants.AlgorithmConstants;
import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.AlgorithmConstraints;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.nio.charset.StandardCharsets.*;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Autowired
    private AlgorithmConstants algorithmConstants;

    @Autowired
    private ExceptionConstants exceptionConstants;

    @Override
    public String getHashedPassword(String passwordToHash, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithmConstants.getShaHashing());
            messageDigest.update(salt.getBytes(UTF_8));
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes(UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(exceptionConstants.getPasswordHashingFailure());
        }
        return hashedPassword;
    }

    @Override
    public List<String> tokenizedString(String string) {
        return Arrays.asList(string.split(" "));
    }

    @Override
    public Calendar getTheWeekBefore() {
        Calendar now = Calendar.getInstance();
        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH) + 1;            // zero based
        int dayNow = now.get(Calendar.DAY_OF_MONTH);

        Calendar weekBeforeNow = new GregorianCalendar(yearNow, monthNow, dayNow - 7);

        return weekBeforeNow;
    }

    @Override
    public Calendar getNow() { return Calendar.getInstance(); }


    @Override
    public boolean containsMovie(List<Movie> movies, String filmImdbId) {
        for (Movie movie : movies) {
            if (movie.getImdbId().equals(filmImdbId))
                return true;
        }
        return false;
    }

}

