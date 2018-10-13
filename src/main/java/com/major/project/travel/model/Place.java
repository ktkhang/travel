package com.major.project.travel.model;

import com.major.project.travel.common.CommonSerialize;

import javax.persistence.*;

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

    @Column(name = "PLACE_NAME")
    private String name;

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
}
