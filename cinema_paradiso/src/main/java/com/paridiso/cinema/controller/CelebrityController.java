package com.paridiso.cinema.controller;

import com.paridiso.cinema.entity.Celebrity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/celebrity")
@RestController
public class CelebrityController {


    //    @Autowired
//    @Qualifier("CelebrityServiceImpl")
//    CelebrityService celebrityService;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity<List<Celebrity>> getCelebrities() {
        return null;
    }

    @RequestMapping(value = "/", method = POST)
    public ResponseEntity<Celebrity> addCelebrity(@RequestBody final Celebrity celebrity) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Celebrity> getCelebrities(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Celebrity> deleteCelebrity(@PathVariable Integer id,
                                                     @RequestBody final Celebrity celebrity) {
        return null;
    }

}
