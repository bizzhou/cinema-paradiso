package com.paridiso.cinema;

import com.paridiso.cinema.entity.Celebrity;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.enumerations.Rated;
import com.paridiso.cinema.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication()
public class CinemaParadisoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaParadisoApplication.class, args);
    }


}
