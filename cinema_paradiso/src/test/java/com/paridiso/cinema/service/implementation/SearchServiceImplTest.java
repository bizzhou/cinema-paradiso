//package com.paridiso.cinema.service.implementation;
//
//import com.paridiso.cinema.entity.Movie;
//import com.paridiso.cinema.persistence.SearchRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import static org.junit.Assert.*;
//
//import java.util.List;
//import java.util.Optional;
//
//public class SearchServiceImplTest {
//
//    @Mock
//    SearchRepository searchRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this) ;
//    }
//
//    @Test
//    public void exactMatch() {
//
//        List<Movie> movies = searchRepository.findAllByTitleMatches("Coco");
//        assertNotNull(movies);
//        assertEquals("no 2 coco", 2, movies.size());
//
//    }
//
//}
