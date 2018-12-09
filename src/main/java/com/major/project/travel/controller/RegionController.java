package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.RegionRequest;
import com.major.project.travel.response.RegionUserResponse;
import com.major.project.travel.service.RegionService;
import com.major.project.travel.service.UserService;
import com.major.project.travel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by HUY on 10/16/2018
 */
@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private UserService userService;

    /**
     * Get List Region
     *
     * @return
     */
    @GetMapping("/all")
    public List<Region> getRegionList() {
        return regionService.list();
    }

    @GetMapping("/findByUid/{uid}")
    public Region findRegionByUid(@PathVariable String uid) throws DataNotFoundException{
        return regionService.findRegionByUid(uid);
    }

    @GetMapping("/findById/{id}")
    public Region findRegionById(@PathVariable String id) throws DataNotFoundException{
        return regionService.findRegionById(id);
    }

    @PostMapping("/create")
    public Region createRegion(@Valid @RequestBody RegionRequest regionRequest, Errors errors) {
        // validate input
        Utility.validateErrorsRequest(errors);
        return regionService.create(regionRequest);
    }

    @GetMapping("/findByUser/{uid}")
    public List<Region> findRegionByUserUid(@PathVariable String uid) throws DataNotFoundException{
        User user = userService.findUserByUid(uid);
        return regionService.findByUser(user);
    }


    /* additional */
    @GetMapping("/findAllByUser/{uid}")
    public List<RegionUserResponse> findAllRegionByUserUid(@PathVariable String uid) throws DataNotFoundException{
        User user = userService.findUserByUid(uid);
        return regionService.findAllByUser(user);
    }


    @PostMapping("/createSample")
    public List<Region> createSampleData(){
        return regionService.createSampleData();
    }
}
