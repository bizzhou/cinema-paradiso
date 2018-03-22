package com.paridiso.cinema.entity;

public class Trailer {

    public final static String trailerLocation = "/tmp";

    private String name;
    private Integer trailerId;

    public static String getTrailerLocation() {
        return trailerLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(Integer trailerId) {
        this.trailerId = trailerId;
    }
}


