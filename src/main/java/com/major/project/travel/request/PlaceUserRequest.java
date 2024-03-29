package com.major.project.travel.request;

import com.major.project.travel.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by HUY on 10/15/2018
 */
public class PlaceUserRequest {

    private String uid;
    @NotNull(message = "User must be empty")
    private String userUid;
    @NotNull(message = "Place must be empty")
    private String placeUid;
    private Long rating;
    private List<Feeling> feelings;
    private List<Album> albums;
    private List<Video> videos;

    // Getter && Setter

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getPlaceUid() {
        return placeUid;
    }

    public void setPlaceUid(String placeUid) {
        this.placeUid = placeUid;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public List<Feeling> getFeelings() {
        return feelings;
    }

    public void setFeelings(List<Feeling> feelings) {
        this.feelings = feelings;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
