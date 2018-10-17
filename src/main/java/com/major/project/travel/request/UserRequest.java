package com.major.project.travel.request;

import com.major.project.travel.model.UserStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by HUY on 10/15/2018
 */
public class UserRequest {

    private String uid;
    @NotNull(message = "User name must be not empty")
    private String userName;
    private long regionVisited;
    private long placeVisited;
    private UserStatus userStatus;

    // Getter && Setter


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getRegionVisited() {
        return regionVisited;
    }

    public void setRegionVisited(long regionVisited) {
        this.regionVisited = regionVisited;
    }

    public long getPlaceVisited() {
        return placeVisited;
    }

    public void setPlaceVisited(long placeVisited) {
        this.placeVisited = placeVisited;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
