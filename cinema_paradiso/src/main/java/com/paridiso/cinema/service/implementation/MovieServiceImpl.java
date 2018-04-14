package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Film;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Trailer;
import com.paridiso.cinema.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Qualifier(value = "MovieServiceImpl")
public class MovieServiceImpl implements FilmService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    LimitationConstants limitationConstants;

    @Autowired
    UtilityService utilityService;

    @Transactional
    @Override
    public Optional<Movie> addMovie(Movie movie) {
//        if (movieRepository.findMovieByImdbId(movie.getImdbId()) != null)
//            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieExists());
        return Optional.of(movieRepository.save(movie));
    }

    @Transactional
    @Override
    public Movie getMovie(String filmId) {
        return movieRepository
                .findMovieByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist()));
    }

    @Transactional
    @Override
    public List<Movie> getMovies() { return movieRepository.findAll(); }

    @Transactional
    @Override
    public void deleteFilm(String filmId) {
        if (movieRepository.findMovieByImdbId(filmId) == null)
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist());
        movieRepository.deleteById(filmId);
    }

    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {
        movieRepository.findMovieByImdbId(movie.getImdbId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getMovieDoesNotExist()));
        return movieRepository.save(movie);
    }

    @Transactional
    @Override
    public void rateFilm(String filmId, Double rating) {
        // add the rating to total rating, then get average
        Movie movie = (Movie) this.getMovie(filmId);
        if (movie.getNumberOfRatings() == null) {
            movie.setNumberOfRatings(1);
        } else {
            movie.setNumberOfRatings(movie.getNumberOfRatings() + 1);
        }
        Double newRatings = (movie.getRating() + rating) / movie.getNumberOfRatings();
        movie.setRating(newRatings);
        movieRepository.save(movie);
    }

    // TODO: movies
    @Override
    public List<Movie> getCarouselMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = (Movie) this.getMovie("tt2380307");
        Movie movie2 = (Movie) this.getMovie("tt5052448");
        Movie movie3 = (Movie) this.getMovie("tt1856101");
        movieList.addAll(Arrays.asList(movie1, movie2, movie3));
        return movieList;
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

    /**
     * Get movies playing now
     * Find movies' release dates in between current date and one week before
     * @return a list of qualified movies
     */
    @Transactional
    @Override
    public Set<Movie> getMoviesPlaying() {
        // get the week before now
        Calendar oneWeekBefore = utilityService.getTheWeekBefore();
        Calendar now = utilityService.getNow();

        // get movies by release date
        Set<Movie> moviesPlaying = new HashSet<>();
        moviesPlaying = movieRepository.findMoviesByReleaseDateBetween(oneWeekBefore, now);

//        moviesPlaying = movieRepository.findMoviesByReleaseDateBefore(Calendar.getInstance());
        //        List<Movie> movieList = new ArrayList<>();
//        Movie movie1 = (Movie) this.getMovie("tt1856101");
//        Movie movie2 = (Movie) this.getMovie("tt2380307");
//        Movie movie3 = (Movie) this.getMovie("tt5726616");
//        Movie movie4 = (Movie) this.getMovie("tt4925292");
//        Movie movie5 = (Movie) this.getMovie("tt5052448");
//        Movie movie6 = (Movie) this.getMovie("tt5723272");
//        movieList.addAll(Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6));
//        return movieList;

        return moviesPlaying;
    }

    @Override
    public List<Film> getTopRating() {
        return null;
    }

    public List<Movie> getTopBoxOffice() {
        return null;
    }

}
