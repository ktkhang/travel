package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.StringListConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
@Entity
@Table(name = "TBL_ALBUMS")
public class Album extends CommonSerialize {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "NAMES")
    @Convert(converter = StringListConverter.class)
    private List<String> names;

    @Column(name = "URL_IMAGES")
    @Convert(converter = StringListConverter.class)
    private List<String> urlImages;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AlbumStatus albumStatus;

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

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(List<String> urlImages) {
        this.urlImages = urlImages;
    }

    public AlbumStatus getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(AlbumStatus albumStatus) {
        this.albumStatus = albumStatus;
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
