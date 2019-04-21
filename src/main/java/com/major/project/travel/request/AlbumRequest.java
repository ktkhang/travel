package com.major.project.travel.request;

import com.major.project.travel.model.AlbumStatus;
import com.major.project.travel.util.StringListConverter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Convert;
import java.util.List;

/**
 * Created by HUY on 3/5/2019
 **/
public class AlbumRequest {
    @NotBlank(message = "Name must be not empty")
    private String name;
    @NotBlank(message = "User must be not empty")
    private String userUid;
    private String regionId;
    private String placeUid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getPlaceUid() {
        return placeUid;
    }

    public void setPlaceUid(String placeUid) {
        this.placeUid = placeUid;
    }
}
