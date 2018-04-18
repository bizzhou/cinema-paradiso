package com.paridiso.cinema.entity;

import com.sun.xml.internal.ws.developer.Serialization;

import javax.persistence.*;

@Entity
@Table(name = "Slides")
@Serialization
public class Slide {

    private Integer slideId;
    private String backgroundImage;
    private Movie movie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getSlideId() {
        return slideId;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "imdbId")
    public Movie getMovie() {
        return movie;
    }

    public void setSlideId(Integer id) {
        this.slideId = id;
    }

    public void setBackgroundImage(String backgroundImage) {
//        try {
//            this.backgroundImage = new URI(backgroundImage);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        this.backgroundImage = backgroundImage;
    }

    public void setMovie(Movie selectedMovie) {
        this.movie = selectedMovie;
    }

    @Override
    public String toString() {
        return "Slide{" +
                "slideId=" + slideId +
                ", backgroundImage='" + backgroundImage + '\'' +
                ", movie=" + movie +
                '}';
    }
}
