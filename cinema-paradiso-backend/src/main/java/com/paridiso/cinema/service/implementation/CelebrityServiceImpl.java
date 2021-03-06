package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.FilmographyWrapper;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.persistence.CelebrityRepository;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.service.CelebrityService;
import com.paridiso.cinema.service.UtilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class CelebrityServiceImpl implements CelebrityService {

    @Autowired
    CelebrityRepository celebrityRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    UtilityService utilityService;

    @Autowired
    MovieRepository movieRepository;

    private static Logger logger = LogManager.getLogger(CelebrityServiceImpl.class);

    @Override
    public List<Celebrity> getCelebrities() {
        return null;
    }

    @Override
    public Optional<Celebrity> getCelebrity(String celebrityId) {
        return celebrityRepository.findCelebrityByCelebrityId(celebrityId);
    }

    @Override
    public Boolean deleteCelebrity(Integer celebrityId) {
        return false;
    }

    @Override
    public Boolean updateCelebrity(Celebrity celebrity) {
        return false;
    }

    @Override
    public Optional<Celebrity> addCelebrity(Celebrity celebrity) {
        if (celebrityRepository.findCelebrityByCelebrityId(celebrity.getCelebrityId()) != null)
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieNotFound());
        return Optional.of(celebrityRepository.save(celebrity));
    }

    @Transactional
    @Override
    public boolean addFilmography(FilmographyWrapper filmography) {
//        List<Movie> celebFilmgraphy = new ArrayList<>();
//        Celebrity celebrity = utilityService.getCelebrity(filmography.getId());
//        filmography.getFilmography().forEach(movieId -> {
//            try {
//                celebFilmgraphy.add(utilityService.getMoive(movieId));
//            } catch (ResponseStatusException e) {
//                logger.info("Cannot find moive " + movieId);
//            }
//        });
//        celebrity.setFilmography(celebFilmgraphy);
//        return celebrityRepository.save(celebrity).getFilmography() != null;


        // get a all movie
        movieRepository.findAll().forEach(movie -> {
            movie.getCasts().forEach(celebrity -> {
                celebrity.getFilmography().add(movie);
            });
        });
        return true;
    }

    @Override
    public List<? extends Film> getFilmography(String id) {
        Celebrity celebrity = utilityService.getCelebrity(id);
        return celebrity.getFilmography();
    }
}
