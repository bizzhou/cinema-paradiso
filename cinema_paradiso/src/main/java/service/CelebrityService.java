package service;

import entity.Celebrity;

import java.util.List;

public interface CelebrityService {

    List<Celebrity> getCelebrities();

    Celebrity getCelebrity(Integer celebrityId);

    boolean deleteCelebrity(Integer celebrityId);

    boolean updateCelebrity(Celebrity celebrity);

    boolean addCelebrity(Celebrity celebrity);

}
