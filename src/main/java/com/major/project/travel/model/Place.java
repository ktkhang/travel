package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@Entity
@Table(name = "TBL_PLACES")
public class Place extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PLACE_COORDINATES")
    private String coordinates;

    @NotNull
    @Column(name = "PLACE_NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLACE_STATUS")
    private PlaceStatus placeStatus;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PlaceUser> placeUsers;

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<PlaceUser> getPlaceUsers() {
        return placeUsers;
    }

    public void setPlaceUsers(List<PlaceUser> placeUsers) {
        this.placeUsers = placeUsers;
    }
}
