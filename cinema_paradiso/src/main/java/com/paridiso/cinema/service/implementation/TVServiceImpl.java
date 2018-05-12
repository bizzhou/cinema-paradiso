package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.constants.MapKeyConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.ListMovieStatus;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.TVRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.persistence.UserTVRatingRepository;
import com.paridiso.cinema.service.FilmService;
import com.paridiso.cinema.service.UtilityService;
import com.paridiso.cinema.utility.MovieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Qualifier(value = "TVServiceImpl")
public class TVServiceImpl implements FilmService {

    @Autowired
    UtilityService utilityService;

    @Autowired
    TVRepository tvRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserTVRatingRepository userRatingRepository;

    @Autowired
    MovieUtility movieUtility;

    @Autowired
    LimitationConstants limitationConstants;

    @Autowired
    MapKeyConstants mapKeyConstants;

    @Override
    public TV addFilm(Film tv) {
        if (tv.getImdbId() == null) {
            String imdbId = tvRepository.findTop1ByOrderByImdbIdDesc().getImdbId();
            Long newId = Long.parseLong(imdbId.replace("tt", "")) + 1;
            tv.setImdbId("tt" + String.valueOf(newId));
        }
        return tvRepository.save((TV)tv);
    }

    @Override
    public TV getFilm(String filmId) {
        TV tv = tvRepository.findTVByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));
        tv.setListMovieStatus(ListMovieStatus.NONE);

        return tv;
    }

    @Override
    public TV getCustomFilm(String filmId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        TV tv = tvRepository
                .findTVByImdbId(filmId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist()));

        return setInitialTVStatus(tv, user);
    }

    @Override
    public List<TV> getMovies() {
        return tvRepository.findAll();
    }

    @Override
    public void deleteFilm(String filmId) {
        if (tvRepository.findTVByImdbId(filmId).get() == null)
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getMovieDoesNotExist());
        tvRepository.deleteById(filmId);
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
    public Set<Film> getFilmInRage(Calendar startDate, Calendar endDate) {
        return null;
    }

    @Override
    public Set<Film> getSimilarFilm(Long filmId) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesTrending(Integer pageNo, Integer pageSize) {
        // get date 1 month before and now
        Calendar daysBeforeNow = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();
        // get tvs with ratings >= 3.5 and released within one month
        Page<TV> tvsPage = tvRepository.findTVSByRegUserRatingBetweenAndReleaseDateBetween(
                limitationConstants.getTrendingRating(), limitationConstants.getRatingLimit(),
                daysBeforeNow, now, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), tvsPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), tvsPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesComingSoon(Integer pageNo, Integer pageSize) {
        Calendar daysAfter = movieUtility.getDaysAfterNow(30);
        Calendar now = movieUtility.getNow();
        System.out.println("fetching tv");
        Page<TV> tvsPage = tvRepository.findTVSByReleaseDateBetween(now, daysAfter, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), tvsPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), tvsPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesPlaying(Integer pageNo, Integer pageSize) {
        // get 31 days before
        Calendar daysBefore = movieUtility.getDaysBeforeNow(limitationConstants.getOneMonthRange());
        Calendar now = movieUtility.getNow();
        // get tvs by release date
        Page<TV> tvsPage = tvRepository.findTVSByReleaseDateBetween(daysBefore, now, new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), tvsPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), tvsPage.getTotalPages());
        return results;
    }

    @Override
    public HashMap<String, Object> getMoviesTopBoxOffice(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public HashMap<String, Object> getMoviesTopRated(Integer pageNo, Integer pageSize) {
        Page<TV> tvsPage = tvRepository
                .findTop20ByOrderByWeightedRankDesc(new PageRequest(pageNo, pageSize));
        HashMap<String, Object> results = new HashMap<>();
        results.put(mapKeyConstants.getMovieLabel(), tvsPage.getContent());
        results.put(mapKeyConstants.getMoviePageLabel(), tvsPage.getTotalPages());
        return results;
    }

    @Override
    public Set<? extends Film> getTopRating() {
        return null;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Double addRating(Integer userId, String filmId, Double rating) {
        TV tv = getFilm(filmId);
        User user = utilityService.getUser(userId);
        if (user.getRole() == Role.ROLE_ADMIN) {
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getAdminCannotRate());
        }
        UserProfile userProfile = user.getUserProfile();
        if (userRatingRepository.findUserTvRatingsByUserAndRatedTv(userProfile, tv).isPresent()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingExists());
        }
        UserTvRating userRating = new UserTvRating();
//        userRating.setRatedTv(tv);
        userRating.setUserRating(rating);
        userRating.setUser(userProfile);
        userRating.setRatedDate(Calendar.getInstance());
        Double newRating = calculateNewRating(rating, tv, userProfile.getCritic());
        userRatingRepository.save(userRating);
        tvRepository.save(tv);
        return newRating;
    }

    @Override
    public Double deleteRating(Integer userProfileId, String filmId) {
        TV tv = getFilm(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserTvRating userRating = userRatingRepository.findUserTvRatingsByUserAndRatedTv(userProfile, tv)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));
        Double oldRating = calculateOldRating(tv, userRating, userProfile.getCritic());
        userRatingRepository.delete(userRating);
        return oldRating;
    }

    @Override
    public Double updateRating(Integer userProfileId, String filmId, Double rating) {
        TV tv = getFilm(filmId);
        UserProfile userProfile = utilityService.getUserProfile(userProfileId);
        UserTvRating userRating = userRatingRepository.findUserTvRatingsByUserAndRatedTv(userProfile, tv)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserRatingNotExists()));
        Double oldRating = calculateOldRating(tv, userRating, userProfile.getCritic());
        tv.setRegUserRating(oldRating);
        Double newRating = calculateNewRating(rating, tv, userProfile.getCritic());
        tv.setRegUserRating(newRating);
        userRating.setUserRating(rating);
        tvRepository.save(tv);
        userRatingRepository.save(userRating);
        return newRating;
    }

    @Transactional
    public TV setInitialTVStatus(TV tv, User user) {
        List<TV> wishListTvs = user.getUserProfile().getWishList().getTvs();
        List<TV> notInterestedTvs = user.getUserProfile().getNotInterestedList().getTvs();

        if (wishListTvs.stream().anyMatch(m -> m.equals(tv)))
            tv.setListMovieStatus(ListMovieStatus.WISHLIST);
        else if (notInterestedTvs.stream().anyMatch(m -> m.equals(tv)))
            tv.setListMovieStatus(ListMovieStatus.NOT_INTERESTED_LIST);
        else
            tv.setListMovieStatus(ListMovieStatus.NONE);

        return tv;
    }

    private Double calculateNewRating(Double rating, TV tv, boolean isCritic) {
        Double tenthPlace = null;
        if (isCritic) {
            Integer oldNumberOfRating = tv.getNumOfCriticRatings();
            Double newRating = (tv.getCriticRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
            tv.setNumOfCriticRatings(oldNumberOfRating + 1);
            tenthPlace = Math.round(newRating * 10.0) / 10.0;
            tv.setCriticRating(tenthPlace);
        } else {
            Integer oldNumberOfRating = tv.getNumOfRegUserRatings();
            Double newRating = (tv.getRegUserRating() * oldNumberOfRating + rating) / (oldNumberOfRating + 1);
            tv.setNumOfRegUserRatings(oldNumberOfRating + 1);
            tenthPlace = Math.round(newRating * 10.0) / 10.0;
            tv.setRegUserRating(tenthPlace);
        }
        return tenthPlace;
    }

    private Double calculateOldRating(TV tv, UserTvRating userRating, boolean isCritic) {
        Double oldRating = null;
        if (isCritic) {
            oldRating = (tv.getCriticRating() * tv.getNumOfCriticRatings() - userRating.getUserRating()) /
                    (tv.getNumOfCriticRatings() - 1);
            oldRating = Math.round(oldRating * 10.0) / 10.0;
            tv.setNumOfRegUserRatings(tv.getNumOfCriticRatings() - 1);
            tv.setCriticRating(oldRating);
        } else {
            oldRating = (tv.getRegUserRating() * tv.getNumOfRegUserRatings() - userRating.getUserRating()) /
                    (tv.getNumOfRegUserRatings() - 1);
            oldRating = Math.round(oldRating * 10.0) / 10.0;
            tv.setNumOfRegUserRatings(tv.getNumOfRegUserRatings() - 1);
            tv.setRegUserRating(oldRating);
        }
        return oldRating;
    }

}
