package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
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
     * Find By Id
     *
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findById/{id}")
    public PlaceUser findPlaceUserById(@PathVariable Long id) throws DataNotFoundException {
        return placeUserService.findById(id);
    }

    /**
     * Create new PlaceUser
     *
     * @param placeUserRequest
     * @param errors
     * @return PlaceUser
     */
    @PostMapping("/create")
    public PlaceUser createPlaceUser(@Valid @RequestBody PlaceUserRequest placeUserRequest, Errors errors) {
        // validate input
        Utility.validateErrorsRequest(errors);

        PlaceUser placeUser = new PlaceUser();

        User user = null;
        try {
            user = userService.findUserByUid(placeUserRequest.getUserUid());
        } catch (Exception e) {
            throw new RestException("User is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        if (user == null || user.getId() == 0) {
            throw new RestException("User is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        placeUser.setUser(user);

        Place place = null;
        try {
            place = placeService.findPlaceByUid(placeUserRequest.getPlaceUid());
        } catch (Exception e) {
            throw new RestException("Place is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        if (place == null || place.getId() == 0) {
            throw new RestException("Place is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        placeUser.setPlace(place);

        placeUser.setFeeling(placeUserRequest.getFeeling());
        placeUser.setAlbums(placeUserRequest.getAlbums());
        placeUser.setVideos(placeUserRequest.getVideos());
        try {
            placeUserService.save(placeUser);
        } catch (Exception e) {
            throw e;
        }
        return placeUser;
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
        PlaceUser placeUser = null;
        try{
            placeUser = placeUserService.findPlaceUserByUid(placeUserRequest.getUid());
        }catch (DataNotFoundException e){
            throw new RestException("PlaceUser is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        placeUser.setFeeling(placeUserRequest.getFeeling());
        placeUser.setAlbums(placeUserRequest.getAlbums());
        placeUser.setVideos(placeUserRequest.getVideos());
        User user = null;
        try {
            user = userService.findUserByUid(placeUserRequest.getUserUid());
        } catch (Exception e) {
            throw new RestException("User is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        if (user == null || user.getId() == 0) {
            throw new RestException("User is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        placeUser.setUser(user);

        Place place = null;
        try {
            place = placeService.findPlaceByUid(placeUserRequest.getPlaceUid());
        } catch (Exception e) {
            throw new RestException("Place is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        if (place == null || place.getId() == 0) {
            throw new RestException("Place is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        placeUser.setPlace(place);
        try {
            placeUserService.update(placeUser);
        } catch (Exception e) {
            throw e;
        }
        return placeUser;
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
        try {
            PlaceUser placeUser = placeUserService.findPlaceUserByUid(uid);
            if (placeUser != null) {
                placeUserService.delete(placeUser);
                return "Delete successfully";
            } else {
                return "PlaceUser does not exist";
            }
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException(String.format("Cannot delete {%s}. Please contact administrator for help.", String.format("placeUserUid = %s", uid)));
        }
    }
}
