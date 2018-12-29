package com.major.project.travel.request;

import com.major.project.travel.model.PlaceStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by HUY on 10/14/2018
 */
public class PlaceRequest {

    private String uid;
    @NotBlank(message = "Place name must be not empty")
    private String name;
    @NotBlank(message = "Place title must be not empty")
    private String title;
    private Double latitude;
    private Double longitude;
    private PlaceStatus placeStatus;
    @NotBlank(message = "Region must be empty")
    private String regionUid;

    // Getter and Setter

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }

    public String getRegionUid() {
        return regionUid;
    }

    public void setRegionUid(String regionUid) {
        this.regionUid = regionUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
