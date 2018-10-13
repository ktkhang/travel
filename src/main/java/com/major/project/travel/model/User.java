package com.major.project.travel.model;

import com.major.project.travel.common.CommonSerialize;

import javax.persistence.*;

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

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_REGION_VISITED")
    private String regionVisited;

    @Column(name = "USER_PLACE_VISITED")
    private String placeVisited;

}
