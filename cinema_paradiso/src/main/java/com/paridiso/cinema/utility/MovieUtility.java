package com.paridiso.cinema.utility;

import com.paridiso.cinema.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Service
public class MovieUtility {

//    @Override
//    public Calendar getNow() {
//        return Calendar.getInstance();
//    }

    public Calendar getNow() {
        Calendar now = Calendar.getInstance();

        // temporarily set current date time to 2017-12-01
        now.set(2017, 11, 1);
        return now;
    }

    public Calendar getDaysBeforeNow(int minusDays) {
        Calendar now = this.getNow();

        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH);
        int dayNow = now.get(Calendar.DAY_OF_MONTH)-minusDays;      // get # days before

        Calendar twoWeekBefore = new GregorianCalendar(yearNow, monthNow, dayNow);
        twoWeekBefore.set(yearNow, monthNow, dayNow);

        return twoWeekBefore;
    }

    public Calendar getDaysAfterNow(int plusDays) {
        Calendar now = this.getNow();

        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH);
        int dayNow = now.get(Calendar.DAY_OF_MONTH)+plusDays;      // get # days after

        Calendar oneWeekAfter = new GregorianCalendar(yearNow, monthNow, dayNow);
        oneWeekAfter.set(yearNow, monthNow, dayNow);

        return oneWeekAfter;
    }

    /**
     * .equals() method
     * @param movies
     * @param filmImdbId
     * @return
     */
    public boolean containsMovie(List<Movie> movies, String filmImdbId) {
        for (Movie movie : movies) {
            if (movie.getImdbId().equals(filmImdbId))
                return true;
        }
        return false;
    }




}
