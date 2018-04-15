package com.paridiso.cinema.utility;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FindCalender {

    @Test
    public void getTheWeekBefore() {
        Calendar now = Calendar.getInstance();
        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH) + 1;            // zero based
        int dayNow = now.get(Calendar.DAY_OF_MONTH);

        Calendar weekBeforeNow = new GregorianCalendar(yearNow, monthNow, dayNow - 7);
        System.out.println("ONE WEEK BEFORE NOW... ");
        System.out.println("year: " + weekBeforeNow.get(Calendar.YEAR));
        System.out.println("month: " + weekBeforeNow.get(Calendar.MONTH));
        System.out.println("day: " + weekBeforeNow.get(Calendar.DAY_OF_MONTH));



    }

}
