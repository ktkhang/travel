package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.Constraint;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@Entity
@Table(name = "TBL_PLACES",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"PLACE_NAME", "REGION_ID"},
                name = Constraint.PLACE_NAME_CONSTRAINT_CODE)})
public class Place extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "PLACE_NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "PLACE_SVG_PATH")
    private String svgPath;

    @NotNull
    @Column(name = "PLACE_TITLE")
    private String title;

    @Column(name = "PLACE_LATITUDE")
    private Double latitude;

    @Column(name = "PLACE_LONGITUDE")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLACE_STATUS")
    private PlaceStatus placeStatus;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
    private Region region;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvgPath() {
        return svgPath;
    }

    public void setSvgPath(String svgPath) {
        this.svgPath = svgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
