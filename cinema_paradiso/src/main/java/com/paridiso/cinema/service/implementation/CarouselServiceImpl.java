package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.Slide;
import com.paridiso.cinema.persistence.SlideRepository;
import com.paridiso.cinema.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ExceptionConstants exceptionConstants;

    @Transactional
    @Override
    public List<Slide> getCarousel() {
        return slideRepository.findAll();
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

}
