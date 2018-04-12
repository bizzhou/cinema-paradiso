package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.persistence.CelebrityRepository;
import com.paridiso.cinema.service.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class CelebrityServiceImpl implements CelebrityService {

    @Autowired
    CelebrityRepository celebrityRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Override
    public List<Celebrity> getCelebrities() {
        return null;
    }

    @Override
    public Optional<Celebrity> getCelebrity(String celebrityId) {
        return celebrityRepository.findCelebrityByCelebrityId(celebrityId);
    }

    @Override
    public boolean deleteCelebrity(Integer celebrityId) {
        return false;
    }

    @Override
    public boolean updateCelebrity(Celebrity celebrity) {
        return false;
    }

    @Override
    public Optional<Celebrity> addCelebrity(Celebrity celebrity) {
        if (celebrityRepository.findCelebrityByCelebrityId(celebrity.getCelebrityId()) != null)
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieNotFound());
        return Optional.of(celebrityRepository.save(celebrity));
    }
}
