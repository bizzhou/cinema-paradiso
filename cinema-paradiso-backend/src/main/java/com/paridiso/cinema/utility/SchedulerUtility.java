package com.paridiso.cinema.utility;

import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.TV;
import com.paridiso.cinema.persistence.MovieRepository;
import com.paridiso.cinema.persistence.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerUtility {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TvRepository tvRepository;

    @Autowired
    LimitationConstants limitationConstants;

    /**
     * Schedule setting weighted rank everyday on 12:30 AM
     *
     * The formula for calculating the Top Rated 250 Titles gives a true Bayesian estimate:
         weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C

         where:
         R = average rating for the movie (mean)
         v = number of ratings for the movie
         m = minimum votes required to be listed in the Top 250 (currently 20)
         C = the mean ratings across the whole report
     */
    @Scheduled(cron = "0 25 16 * * ?") // second, minute, hour, day of month, month, day(s) of week
    private void setRatedMovieScores() {
        List<Movie> movies = movieRepository.findAll();
        double v, m, r, weightedRank;

        double c = movieRepository.findAvgCriticRatings() + movieRepository.findAvgRegUserRatings();
        for (Movie movie: movies) {
            v = movie.getNumOfCriticRatings() + movie.getNumOfRegUserRatings();
            r = movie.getCriticRating() + movie.getRegUserRating();
            m = limitationConstants.getMinNumOfRatingsForWeightedRank();

            weightedRank = (v / (v + m)) * r + (m / (v + m)) * c;
            movie.setWeightedRank(weightedRank);
            movieRepository.save(movie);
        }
        System.out.println("done");
    }

    @Scheduled(cron = "0 53 20 * * ?") // second, minute, hour, day of month, month, day(s) of week
    private void setRatedTVScores() {
        List<TV> tvs = tvRepository.findAll();
        double v, m, r, weightedRank;

        double c = tvRepository.findAvgCriticRatings() + tvRepository.findAvgRegUserRatings();
        for (TV tv: tvs) {
            v = tv.getNumOfCriticRatings() + tv.getNumOfRegUserRatings();
            r = tv.getCriticRating() + tv.getRegUserRating();
            m = limitationConstants.getMinNumOfRatingsForWeightedRank();

            weightedRank = (v / (v + m)) * r + (m / (v + m)) * c;
            tv.setWeightedRank(weightedRank);
            tvRepository.save(tv);
        }
        System.out.println("done");
    }

}
