package com.major.project.travel.controller;

import com.major.project.travel.model.Place;
import com.major.project.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@Controller
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("/all")
    public List<Place> getPlaceList(){
        System.out.println("-------Get Place-------");
        List<Place> test = null;
        try{
            System.out.println("Find List");
            test = placeService.list();
            System.out.println("Second List : ");
            for (Place place: test){
                System.out.println("--- name :" + place.getName() );
                System.out.println("---Coordinate :" + place.getCoordinates());
            }
        }
        catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return test;
    }

}
