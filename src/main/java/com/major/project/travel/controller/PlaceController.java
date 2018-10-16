package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.PlaceStatus;
import com.major.project.travel.model.Region;
import com.major.project.travel.request.PlaceRequest;
import com.major.project.travel.service.PlaceService;
import com.major.project.travel.service.RegionService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @Autowired
    private RegionService regionService;

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

    /**
     * Create new Place
     * @param placeRequest
     * @return
     */
    @PostMapping("/create")
    public Place createPlace(@Valid @RequestBody PlaceRequest placeRequest, Errors errors){
        // validate input
        Utility.validateErrorsRequest(errors);

        Place place = new Place();
        place.setTitle(placeRequest.getTitle());
        place.setSvgPath(placeRequest.getSvgPath());
        place.getLatitude(placeRequest.getLatitude());
        place.getLongitude(placeRequest.getLongitude());
        place.setPlaceStatus(PlaceStatus.AVAILABLE);
        Region region = null;
        try {
            region = regionService.findRegionByUid(placeRequest.getRegionUid());
        } catch (Exception e) {
            throw new RestException("Region is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        if (region == null || region.getId() == 0) {
            throw new RestException("Region is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        place.setRegion(region);
        try{
            placeService.save(place);
        }catch (Exception e ){
            throw e;
        }
        return place;
    }
}
