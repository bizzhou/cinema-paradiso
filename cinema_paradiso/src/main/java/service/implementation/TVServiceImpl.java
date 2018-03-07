package service.implementation;

import entity.Film;
import entity.Trailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import persistence.FilmDao;
import service.FilmService;
import service.UtilityService;

import java.util.Date;
import java.util.List;

public class TVServiceImpl implements FilmService {


    @Autowired
    @Qualifier("MovieDaoImpl")
    FilmDao filmDao;

    @Autowired
    @Qualifier("InputUtilityServiceImpl")
    UtilityService utilityService;


    @Override
    public boolean addFilm(Film film) {
        return false;
    }

    @Override
    public Film getFilm(Film film) {
        return null;
    }

    @Override
    public Film deleteFilm(Long filmId) {
        return null;
    }

    @Override
    public boolean rateFilm(Long filmId) {
        return false;
    }

    @Override
    public void updateFilmRating(Long filmId) {

    }

    @Override
    public List<Trailer> getTrailers(Long filmId) {
        return null;
    }

    @Override
    public boolean updateTrailer(Long filmId, Integer trailerId) {
        return false;
    }

    @Override
    public List<Film> getFilmInRage(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Film> getSimilarFilm(Long filmId) {
        return null;
    }

    @Override
    public List<Film> getTrending() {
        return null;
    }

    @Override
    public List<Film> getPlaying() {
        return null;
    }

    @Override
    public List<Film> getTopRating() {
        return null;
    }
}
