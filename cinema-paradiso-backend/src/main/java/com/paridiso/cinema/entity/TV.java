package com.paridiso.cinema.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TVs",  uniqueConstraints = @UniqueConstraint(columnNames = "imdbId"))
public class TV extends Film {

    private Integer duration;
    private Double audienceRating;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getAudienceRating() {
        return audienceRating;
    }

    public void setAudienceRating(Double audienceRating) {
        this.audienceRating = audienceRating;
    }

    @Override
    public String toString() {
        return "TV{" +
                "duration=" + duration +
                ", audienceRating=" + audienceRating +
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
                ", reviews=" + reviews +
                ", trailers=" + trailers +
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
