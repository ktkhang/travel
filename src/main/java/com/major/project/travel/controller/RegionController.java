package com.major.project.travel.controller;

import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.Region;
import com.major.project.travel.request.RegionRequest;
import com.major.project.travel.service.RegionService;
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
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * Get List Region
     *
     * @return
     */
    @GetMapping("/all")
    public List<Region> getRegionList() {
        return regionService.list();
    }

    @PostMapping("/create")
    public Region createRegion(@Valid @RequestBody RegionRequest regionRequest, Errors errors) {
        // validate input
        Utility.validateErrorsRequest(errors);

        return regionService.create(regionRequest);
    }

    @PostMapping("/createSample")
    public List<Region> createSampleData(){
        return regionService.createSampleData();
    }
}
