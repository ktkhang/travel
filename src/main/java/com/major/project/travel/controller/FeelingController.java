package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.Place;
import com.major.project.travel.service.FeelingService;
import com.major.project.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by HUY on 02/01/2018
 */
@RestController
@RequestMapping("/feelings")
public class FeelingController {

    @Autowired
    private FeelingService feelingService;

    @Autowired
    private PlaceService placeService;

    /**
     * Find by Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findFeeling/{uid}")
    public List<Feeling> findFeelingByPlace(@PathVariable String uid) throws DataNotFoundException {
        Place place = placeService.findPlaceByUid(uid);
        return feelingService.findFeelingByPlace(place);
    }
}
