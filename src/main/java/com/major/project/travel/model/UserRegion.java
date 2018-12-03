package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.Constraint;
import com.major.project.travel.util.StringListConverter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by HUY on 11/26/2018
 */
@Entity
@Table(name = "TBL_USER_REGION",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"USER_ID", "REGION_ID"},
                name = Constraint.USER_REGION_CONSTRAINT)})
public class UserRegion extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
    private Region region;

    @Column(name = "UR_FEELING")
    private String feeling;

    @Column(name = "UR_ALBUM")
    @Convert(converter = StringListConverter.class)
    private List<String> albums;

    @Column(name = "UR_VIDEO")
    @Convert(converter = StringListConverter.class)
    private List<String> videos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}
