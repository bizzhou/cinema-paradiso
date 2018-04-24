package com.paridiso.cinema.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Movies", uniqueConstraints = @UniqueConstraint(columnNames = "imdbId"))
public class Movie extends Film {

    private Integer runtime;
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

    @Override
    public String toString() {
        return "Movie{" +
                "runtime=" + runtime +
                ", boxOffice=" + boxOffice +
                ", imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated=" + rated +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", awards=" + awards +
                ", photos=" + photos +
                ", director=" + director +
                ", casts=" + casts +
                ", trailers=" + trailers +
                ", reviews=" + reviews +
                ", plot='" + plot + '\'' +
                ", movieInfo='" + movieInfo + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", poster='" + poster + '\'' +
                ", regUserRating=" + regUserRating +
                ", numOfRegUserRatings=" + numOfRegUserRatings +
                ", production='" + production + '\'' +
                ", website=" + website +
                '}';
    }
}
