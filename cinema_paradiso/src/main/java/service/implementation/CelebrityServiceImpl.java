package service.implementation;

import entity.Celebrity;
import service.CelebrityService;

import java.util.List;

public class CelebrityServiceImpl implements CelebrityService {
    @Override
    public List<Celebrity> getCelebrities() {
        return null;
    }

    @Override
    public Celebrity getCelebrity(Integer celebrityId) {
        return null;
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
    public boolean addCelebrity(Celebrity celebrity) {
        return false;
    }
}
