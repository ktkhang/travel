package com.major.project.travel.controller;

import com.major.project.travel.model.Region;
import com.major.project.travel.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
