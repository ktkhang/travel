package com.major.project.travel.response;

import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.model.PlaceStatus;
import com.major.project.travel.model.PlaceUser;

import java.util.List;

/**
 * Created by ktKhang on 09, Dec, 2018
 **/
public class PlaceUserResponse extends CommonSerialize {
    private Long id;
    private String name;
    private String svgPath;
    private String title;
    private Double latitude;
    private Double longitude;
    private PlaceStatus placeStatus;
    private List<PlaceUser> placeUsers;
    private PlaceUser placeUserDetail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvgPath() {
        return svgPath;
    }

    public void setSvgPath(String svgPath) {
        this.svgPath = svgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }

    public List<PlaceUser> getPlaceUsers() {
        return placeUsers;
    }

    public void setPlaceUsers(List<PlaceUser> placeUsers) {
        this.placeUsers = placeUsers;
    }

    public PlaceUser getPlaceUserDetail() {
        return placeUserDetail;
    }

    public void setPlaceUserDetail(PlaceUser placeUserDetail) {
        this.placeUserDetail = placeUserDetail;
    }
}
