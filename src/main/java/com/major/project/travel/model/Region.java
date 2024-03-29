package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HUY on 10/14/2018
 */
@Entity
@Table(name = "TBL_REGIONS")
public class Region extends CommonSerialize {

    @Id
    @Column(name = "ID")
    private String id;

    @NotNull
    @Column(name = "REGION_NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "REGION_TITLE")
    private String title;

//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER )
//    @JoinTable(name = "USER_REGION",
//    joinColumns = {@JoinColumn(name = "REGION_ID",referencedColumnName = "ID")},
//    inverseJoinColumns = {@JoinColumn(name ="USER_ID", referencedColumnName = "ID")})
//    @JsonIgnore
//    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserRegion> userRegions;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Place> placeList;

    // Getter and Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserRegion> getUserRegions() {
        return userRegions;
    }

    public void setUserRegions(List<UserRegion> userRegions) {
        this.userRegions = userRegions;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }
}
