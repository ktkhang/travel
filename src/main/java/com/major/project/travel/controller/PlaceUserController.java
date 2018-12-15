package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.FeelingPlaceRequest;
import com.major.project.travel.request.PlaceUserRequest;
import com.major.project.travel.service.PlaceService;
import com.major.project.travel.service.PlaceUserService;
import com.major.project.travel.service.UserService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by HUY on 10/16/2018
 */
@RestController
@RequestMapping("/placeUser")
public class PlaceUserController {

    @Autowired
    private PlaceUserService placeUserService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<PlaceUser> getPlaceUserList(){
        return placeUserService.list();
    }

    /**
     * Create new PlaceUser
     *
     * @param placeUserRequest
     * @param errors
     * @return PlaceUser
     */
    @PostMapping("/create")
    public PlaceUser createPlaceUser(@Valid @RequestBody PlaceUserRequest placeUserRequest, Errors errors)
    throws DataNotFoundException{
        // validate input
        Utility.validateErrorsRequest(errors);
        return placeUserService.save(placeUserRequest);
    }

    /**
     * Update PlaceUser
     *
     * @param placeUserRequest
     * @param errors
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/update")
    public PlaceUser updatePlaceUser(@Valid @RequestBody PlaceUserRequest placeUserRequest, Errors errors) throws DataNotFoundException {
        // validate input
        Utility.validateErrorsRequest(errors);
        return placeUserService.update(placeUserRequest);
    }

    /**
     * Delete PlaceUser
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/{uid}")
    public String deletePlaceUser(@PathVariable String uid) throws DataNotFoundException {
        return placeUserService.delete(uid);
    }

    @PostMapping("/addPost")
    public PlaceUser addPost(@Valid @RequestBody FeelingPlaceRequest feelingPlaceRequest, Errors errors)
            throws DataNotFoundException {
        // validate input
        Utility.validateErrorsRequest(errors);
        return placeUserService.addPost(feelingPlaceRequest);
    }
}
