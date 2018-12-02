package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.*;
import com.major.project.travel.service.*;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.crypto.Data;
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

    @Autowired
    private UserService userService;

    /**
     * Get List Place
     *
     * @return
     */
    @GetMapping("/all")
    public List<Place> getPlaceList() {
        return placeService.list();
    }

    /**
     * Find By Id
     *
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findById/{id}")
    public Place findPlaceById(@PathVariable Long id) throws DataNotFoundException {
        return placeService.findById(id);
    }

    /**
     * Find by Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findByUid/{uid}")
    public Place findByUid(@PathVariable String uid) throws DataNotFoundException {
        return placeService.findPlaceByUid(uid);
    }

    /**
     * Create new Place
     *
     * @param placeRequest
     * @param errors
     * @return Place
     */
    @PostMapping("/create")
    public Place createPlace(@Valid @RequestBody PlaceRequest placeRequest, Errors errors)
        throws DataNotFoundException{
        // validate input
        Utility.validateErrorsRequest(errors);
        return placeService.save(placeRequest);
    }

    /**
     * Update Place
     *
     * @param placeRequest
     * @param errors
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/update")
    public Place updatePlace(@Valid @RequestBody PlaceRequest placeRequest, Errors errors)
        throws DataNotFoundException{
        // validate input
        Utility.validateErrorsRequest(errors);
        return placeService.update(placeRequest);
    }

    /**
     * findPlaceByRegionUid
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findByRegionUid/{uid}")
    public List<Place> findPlacesByRegionUid(@PathVariable String uid) throws DataNotFoundException{
        Region region = regionService.findRegionByUid(uid);
        return placeService.findByRegion(region);
    }

    /**
     * findPlacesByUserUid
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findByUserUid/{uid}")
    public List<Place> findPlacesByUserUid(@PathVariable String uid) throws DataNotFoundException{
        User user = userService.findUserByUid(uid);
        return  placeService.findByUser(user);
    }

    /**
     * Delete Place
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/{uid}")
    public String deletePlace(@PathVariable String uid) throws DataNotFoundException {
        return placeService.delete(uid);
    }
}
