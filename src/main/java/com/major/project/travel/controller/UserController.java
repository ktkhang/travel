package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;
import com.major.project.travel.model.User;
import com.major.project.travel.request.UserCreationRequest;
import com.major.project.travel.service.FeelingService;
import com.major.project.travel.service.PlaceService;
import com.major.project.travel.service.RoleService;
import com.major.project.travel.service.UserService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private FeelingService feelingService;

    @GetMapping("/all")
    public List<User> getUserList() {
        return userService.list();
    }

    @PostMapping("/createAdmin")
    public User createAdminUser(@Valid @RequestBody UserCreationRequest userRequest, Errors errors) throws DataNotFoundException {
        Utility.validateErrorsRequest(errors);
        return userService.createAdminUser(userRequest);
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

    /**
     * find Post by User9ikm
     *
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/findPostByUser/{uid}")
    public List<Feeling> findPostByUser(@PathVariable String uid) throws DataNotFoundException{
        User user = userService.findUserByUid(uid);
        return feelingService.findPostByUser(user);

    }
}
