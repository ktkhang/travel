package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.StringListConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTOS")
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Photo> photos;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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
