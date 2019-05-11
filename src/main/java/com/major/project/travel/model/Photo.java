package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ktKhang on 18, Apr, 2019
 **/
@Entity
@Table(name = "TBL_PHOTOS")
public class Photo extends CommonSerialize {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PhotoStatus photoStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "ALBUM_ID", referencedColumnName = "ID")
    private Album album;

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

    public PhotoStatus getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(PhotoStatus photoStatus) {
        this.photoStatus = photoStatus;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
