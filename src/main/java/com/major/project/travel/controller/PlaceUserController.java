package com.major.project.travel.controller;

import com.major.project.travel.model.PlaceUser;
import com.major.project.travel.service.PlaceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HUY on 10/16/2018
 */
@RestController
@RequestMapping("/placeUser")
public class PlaceUserController {

    @Autowired
    private PlaceUserService placeUserService;

    @GetMapping("/all")
    public List<PlaceUser> getPlaceUserList(){
        return placeUserService.list();
    }
}
