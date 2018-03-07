package com.rottentomatoes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CinemaParadisoMain {
    public static void main(String[] args) {
        SpringApplication.run(CinemaParadisoMain.class, args);
    }
}
