package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /**
     * Get List Place
     * @return
     */
    @GetMapping("/all")
    public List<Place> getPlaceList(){
        return placeService.list();
    }

    /**
     * Find By Id
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findById/{id}")
    public Place findPlaceById(@PathVariable Long id)throws DataNotFoundException {
        return placeService.findById(id);
    }

    @PostMapping("/create")
    public Place createPlace(){
        Place place = new Place();
        return place;
    }
}
