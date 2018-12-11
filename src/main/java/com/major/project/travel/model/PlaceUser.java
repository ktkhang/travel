package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.Constraint;
import com.major.project.travel.util.StringListConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Entity
@Table(name = "TBL_PLACE_USER",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"USER_ID", "PLACE_ID"},
        name = Constraint.PLACE_USER_CONSTRAINT_CODE)})
public class PlaceUser extends CommonSerialize {

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
    @JoinColumn(name = "PLACE_ID", referencedColumnName = "ID")
    private Place place;

//    @Column(name = "PU_FEELING")
//    private String feeling;

//    @Column(name = "PU_ALBUM")
//    @Convert(converter = StringListConverter.class)
//    private List<String> albums;

    @OneToMany(mappedBy = "placeUser", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Feeling> feelings;

    @OneToMany(mappedBy = "placeUser", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Album> albums;

    @Column(name = "PU_VIDEO")
    @Convert(converter = StringListConverter.class)
    private List<String> videos;

    // Getter and Setter

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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}
