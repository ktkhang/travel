package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserStatus;
import com.major.project.travel.request.UserRequest;
import com.major.project.travel.service.PlaceService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/all")
    public List<User> getUserList() {
        return userService.list();
    }

    @GetMapping("/show/{uid}")
    public User showByUid(@PathVariable String uid) throws DataNotFoundException{
        return userService.findUserByUid(uid);
    }

    /**
     * Delete User
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/{uid}")
    public String deleteUser(@PathVariable String uid) throws DataNotFoundException {
        return userService.delete(uid);
    }

    /**
     * findUsersByPlaceUid
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findByPlace/{uid}")
    public List<User> findUsersByPlaceUid(@PathVariable String uid) throws DataNotFoundException{
        Place place = placeService.findPlaceByUid(uid);
        return userService.findByPlace(place);
    }
}
