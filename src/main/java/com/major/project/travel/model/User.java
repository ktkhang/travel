package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.Constraint;
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
@Table(name = "TBL_USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID"},
name = Constraint.USER_CONSTRAINT_CODE)})
public class User extends CommonSerialize {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long userID;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_EMAIL")
    private String email;

    @NotNull
    @Column(name = "USER_AVATAR")
    private String avatar;

    @Column(name = "USER_REGION_VISITED")
    private long regionVisited;

    @Column(name = "USER_PLACE_VISITED")
    private long placeVisited;

    @Enumerated(EnumType.STRING)
    @Column(name ="USER_STATUS")
    private UserStatus userStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")
    private Role role;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getRegionVisited() {
        return regionVisited;
    }

    public void setRegionVisited(long regionVisited) {
        this.regionVisited = regionVisited;
    }

    public long getPlaceVisited() {
        return placeVisited;
    }

    public void setPlaceVisited(long placeVisited) {
        this.placeVisited = placeVisited;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
