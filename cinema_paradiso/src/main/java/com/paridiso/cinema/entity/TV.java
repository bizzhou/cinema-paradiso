package com.paridiso.cinema.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TVs",  uniqueConstraints = @UniqueConstraint(columnNames = "imdbId"))
public class TV extends Film {

    private Integer duration;
    private Double autdienceRating;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getAutdienceRating() {
        return autdienceRating;
    }

    public void setAutdienceRating(Double autdienceRating) {
        this.autdienceRating = autdienceRating;
    }

    @Override
    public String toString() {
        return "TV{" +
                "duration=" + duration +
                ", autdienceRating=" + autdienceRating +
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
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                ", production='" + production + '\'' +
                ", website=" + website +
                '}';
    }
}
