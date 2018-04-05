package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Genre;
import com.paridiso.cinema.entity.enumerations.Rated;

import javax.persistence.*;
import java.net.URI;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "Movies",  uniqueConstraints = @UniqueConstraint(columnNames = "imdbId"))
public class Movie extends Film {

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "boxOffice")
    private Long boxOffice;

    public Movie() {
    }

    public Integer getRunTime() {
        return runtime;
    }

    public void setRunTime(Integer runTime) {
        this.runtime = runTime;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

}
