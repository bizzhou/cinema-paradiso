package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Movie;
import com.paridiso.cinema.entity.Slide;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.enumerations.ListMovieStatus;
import com.paridiso.cinema.persistence.SlideRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ExceptionConstants exceptionConstants;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<Slide> getCarousel() {
        List<Slide> slides = slideRepository.findAll();
        for (Slide slide: slides)
            slide.getMovie().setListMovieStatus(ListMovieStatus.NONE);
        return slides;
    }

    @Transactional
    @Override
    public Slide updateSlide(Slide slide) {
        slideRepository.findSlideBySlideId(slide.getSlideId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, exceptionConstants.getSlideNotFound()));
        return slideRepository.save(slide);
    }

    @Transactional
    @Override
    public Slide addSlide(Slide slide) {
        return slideRepository.save(slide);
    }

    /**
     * Set whether the movie is in wish list, not interested list, or neither
     * @param slides
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public List<Slide> setInitialMovieStatus(List<Slide> slides, Integer userId) {
        // get wish list
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));

        List<Movie> wishListMovies = user.getUserProfile().getWishList().getMovies();
        List<Movie> notInterestedMovies = user.getUserProfile().getNotInterestedList().getMovies();

        // mark whether the movie exists in the list
        for (Slide slide: slides) {
            if (wishListMovies.stream().anyMatch(movie -> movie.equals(slide.getMovie())))
                slide.getMovie().setListMovieStatus(ListMovieStatus.WISHLIST);
            else if (notInterestedMovies.stream().anyMatch(movie -> movie.equals(slide.getMovie())))
                slide.getMovie().setListMovieStatus(ListMovieStatus.NOT_INTERESTED_LIST);
            else
                slide.getMovie().setListMovieStatus(ListMovieStatus.NONE);
        }

        return slides;
    }


}
