package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.xml.soap.Text;

/**
 * Created by HUY on 10/15/2018
 */
public class RegionRequest {
    @NotBlank(message = "Region ID must be not empty")
    private String id;
    @NotBlank(message = "Region name must be not empty")
    private String name;
    @NotBlank(message = "Region title must be not empty")
    private String title;
    @NotBlank(message = "Region coordinate must be not empty")
    private String coordinate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
