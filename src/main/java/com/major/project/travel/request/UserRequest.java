package com.major.project.travel.request;

import com.major.project.travel.model.UserStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by HUY on 10/15/2018
 */
public class UserRequest {

    @NotNull(message = "User name must be not empty")
    private String userName;
    private String regionVisited;
    private String placeVisited;
    private UserStatus userStatus;

    // Getter && Setter

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegionVisited() {
        return regionVisited;
    }

    public void setRegionVisited(String regionVisited) {
        this.regionVisited = regionVisited;
    }

    public String getPlaceVisited() {
        return placeVisited;
    }

    public void setPlaceVisited(String placeVisited) {
        this.placeVisited = placeVisited;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
