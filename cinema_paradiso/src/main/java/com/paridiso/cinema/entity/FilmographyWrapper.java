package com.paridiso.cinema.entity;

import java.util.List;

public class FilmographyWrapper {

	private String id;
	private List<String> filmography;

	public FilmographyWrapper() {
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String[] getFilmography() {
//        return filmography;
//    }
//
//    public void setFilmography(String[] filmography) {
//        this.filmography = filmography;
//    }


    public List<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<String> filmography) {
        this.filmography = filmography;
    }

    @Override
    public String toString() {
        return "FilmographyWrapper{" +
                "celebrityId='" + id + '\'' +
                ", filmography=" + filmography +
                '}';
    }
}