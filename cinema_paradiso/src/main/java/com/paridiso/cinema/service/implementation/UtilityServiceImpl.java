package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.AlgorithmConstants;
import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.persistence.CelebrityRepository;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Autowired
    private AlgorithmConstants algorithmConstants;

    @Autowired
    private ExceptionConstants exceptionConstants;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CelebrityRepository celebrityRepository;

    @Override
    public Movie getMoive(String movieId) {
        return movieRepository
                .findMovieByImdbId(movieId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
    }

    @Override
    public UserProfile getUserProfile(Integer id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getProfileNotFound()));
    }

    @Override
    public Celebrity getCelebrity(String id) {
        return celebrityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getCelebrityNotFound()));
    }


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
    public Collection<? extends Film> shrinkMovieSize(Collection<? extends Film> films) {
        films.forEach(movie -> {
            movie.setCasts(null);
            movie.setPhotos(null);
            movie.setDirector(null);
            movie.setReviews(null);
            movie.setAwards(null);
            movie.setGenres(null);
            movie.setLanguage(null);
            movie.setPlot(null);
            movie.setWebsite(null);
        });
        return films;
    }

    @Override
    public Collection<Celebrity> shrinkCelebritySize(Collection<Celebrity> celebrities) {
        celebrities.forEach(celebrity -> {
            celebrity.setBiography(null);
            celebrity.setFilmography(null);
        });
        return celebrities;
    }

}

