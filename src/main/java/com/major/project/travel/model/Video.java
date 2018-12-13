package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.StringListConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by HUY on 12/13/2018
 */
@Entity
@Table(name = "TBL_VIDEOS")
public class Video extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "VIDEOS")
    @Convert(converter = StringListConverter.class)
    private List<String> videos;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private VideoStatus videoStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "USER_REGION_ID", referencedColumnName = "ID")
    private UserRegion userRegion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "PLACE_USER_ID", referencedColumnName = "ID")
    private PlaceUser placeUser;

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

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public VideoStatus getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(VideoStatus videoStatus) {
        this.videoStatus = videoStatus;
    }

    public UserRegion getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(UserRegion userRegion) {
        this.userRegion = userRegion;
    }

    public PlaceUser getPlaceUser() {
        return placeUser;
    }

    public void setPlaceUser(PlaceUser placeUser) {
        this.placeUser = placeUser;
    }
}
