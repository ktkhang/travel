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
     * Create new User
     *
     * @param userRequest
     * @param errors
     * @return
     */
    @PostMapping("/create")
    public User createUser(@Valid @RequestBody UserRequest userRequest, Errors errors) {
        // validate input
        Utility.validateErrorsRequest(errors);

        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPlaceVisited(userRequest.getPlaceVisited());
        user.setRegionVisited(userRequest.getRegionVisited());
        user.setUserStatus(UserStatus.ACTIVE);
        try {
            userService.save(user);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    /**
     * Update User
     *
     * @param userRequest
     * @param errors
     * @return
     */
    @PutMapping("/update")
    public User updateUser(@Valid @RequestBody UserRequest userRequest, Errors errors) {
        // validate input
        Utility.validateErrorsRequest(errors);
        User user = null;
        try {
            user = userService.findUserByUid(userRequest.getUid());
        } catch (DataNotFoundException e) {
            throw new RestException("User is not existed", HttpServletResponse.SC_NOT_FOUND);
        }
        user.setUserName(userRequest.getUserName());
        user.setPlaceVisited(userRequest.getPlaceVisited());
        user.setRegionVisited(userRequest.getRegionVisited());
        user.setUserStatus(userRequest.getUserStatus());
        try {
            userService.update(user);
        } catch (Exception e) {
            throw e;
        }
        return user;
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
        try {
            User user = userService.findUserByUid(uid);
            if (user != null) {
                userService.delete(user);
                return "Delete successfully";
            } else {
                return "User does not exist";
            }
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException(String.format("Cannot delete {%s}. Please contact administrator for help.", String.format("userUid = %s", uid)));
        }
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

    /**
     * findUsersByPlaceUid
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findByádfPlace/{uid}")
    public List<User> findUsersByPlaceUiadsfasdd(@PathVariable String uid) throws DataNotFoundException{
        Place place = placeService.findPlaceByUid(uid);
        return userService.findByPlace(place);
    }
}
