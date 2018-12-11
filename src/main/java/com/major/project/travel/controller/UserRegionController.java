package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.UserRegion;
import com.major.project.travel.request.FeelingRegionRequest;
import com.major.project.travel.service.UserRegionService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
@RestController
@RequestMapping("/userRegion")
public class UserRegionController {
    @Autowired
    private UserRegionService userRegionService;

    @PostMapping("/addPost")
    public UserRegion addPost(@Valid @RequestBody FeelingRegionRequest feelingRegionRequest, Errors errors)
            throws DataNotFoundException {
        // validate input
        Utility.validateErrorsRequest(errors);
        return userRegionService.addPost(feelingRegionRequest);
    }
}
