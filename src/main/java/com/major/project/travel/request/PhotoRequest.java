package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

public class PhotoRequest {
    @NotBlank(message = "User must be not empty")
    private String userUid;
    @NotBlank(message = "Album must be not empty")
    private String albumUid;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getAlbumUid() {
        return albumUid;
    }

    public void setAlbumUid(String albumUid) {
        this.albumUid = albumUid;
    }
}
