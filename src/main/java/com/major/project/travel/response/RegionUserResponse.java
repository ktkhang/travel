package com.major.project.travel.response;

import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.model.UserRegion;

import java.util.List;

/**
 * Created by ktKhang on 09, Dec, 2018
 **/
public class RegionUserResponse extends CommonSerialize {
    private String id;
    private String name;
    private String title;
    private List<UserRegion> userRegions;
    private List<PlaceUserResponse> placeList;
    private UserRegion regionUserDetail;

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

    public List<UserRegion> getUserRegions() {
        return userRegions;
    }

    public void setUserRegions(List<UserRegion> userRegions) {
        this.userRegions = userRegions;
    }

    public List<PlaceUserResponse> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<PlaceUserResponse> placeList) {
        this.placeList = placeList;
    }

    public UserRegion getRegionUserDetail() {
        return regionUserDetail;
    }

    public void setRegionUserDetail(UserRegion regionUserDetail) {
        this.regionUserDetail = regionUserDetail;
    }
}
