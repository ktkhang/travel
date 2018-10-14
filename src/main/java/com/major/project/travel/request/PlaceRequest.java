package com.major.project.travel.request;

import com.major.project.travel.model.PlaceStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by HUY on 10/14/2018
 */
public class PlaceRequest {

    private String uid;
    private String coordinate;
    private PlaceStatus placeStatus;
    @NotNull(message = "Place name must be not empty")
    private String name;
    @NotNull(message = "Region must be empty")
    private String regionUid;
}
