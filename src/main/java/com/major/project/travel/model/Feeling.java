package com.major.project.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.common.CommonSerialize;
import com.major.project.travel.util.Constraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
@Entity
@Table(name = "TBL_FEELINGS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"TOPIC","USER_REGION_ID"}, name = Constraint.TOPIC_REGION_CONSTRAINT_CODE),
                @UniqueConstraint(columnNames = {"TOPIC","PLACE_USER_ID"}, name = Constraint.TOPIC_PLACE_CONSTRAINT_CODE)}
)
public class Feeling extends CommonSerialize {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "TOPIC")
    private String topic;

    @NotNull
    @Column(name = "CONTENT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private FeelingStatus feelingStatus;

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeelingStatus getFeelingStatus() {
        return feelingStatus;
    }

    public void setFeelingStatus(FeelingStatus feelingStatus) {
        this.feelingStatus = feelingStatus;
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
