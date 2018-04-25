package com.paridiso.cinema.entity;

import java.util.List;

public class FilmographyWrapper {

	private String celebrityId;
	private List<String> filmography;

	public FilmographyWrapper() {
	}

    public String getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(String celebrityId) {
        this.celebrityId = celebrityId;
    }

    public List<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<String> filmography) {
        this.filmography = filmography;
    }
}