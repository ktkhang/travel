package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HUY on 10/3/2018
 */
@Entity
@Table(name = "TBL_USERS")
public class User extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "USER_REGION_VISITED")
    private String regionVisited;

    @Column(name = "USER_PLACE_VISITED")
    private String placeVisited;

    @Enumerated(EnumType.STRING)
    @Column(name ="USER_STATUS")
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "users")
    private Set<Region> region = new HashSet<Region>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value =  FetchMode.SUBSELECT)
    private List<PlaceUser> placeUsers;

    // Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegionVisited() {
        return regionVisited;
    }

    public void setRegionVisited(String regionVisited) {
        this.regionVisited = regionVisited;
    }

    public String getPlaceVisited() {
        return placeVisited;
    }

    public void setPlaceVisited(String placeVisited) {
        this.placeVisited = placeVisited;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Set<Region> getRegion() {
        return region;
    }

    public void setRegion(Set<Region> region) {
        this.region = region;
    }

    public List<PlaceUser> getPlaceUsers() {
        return placeUsers;
    }

    public void setPlaceUsers(List<PlaceUser> placeUsers) {
        this.placeUsers = placeUsers;
    }
}
