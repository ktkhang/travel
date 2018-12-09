package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserRegion;

import java.io.Serializable;

/**
 * Created by HUY on 11/26/2018
 */
public interface UserRegionDao extends CommonHibernateInteface<Serializable, UserRegion> {
    /**
     * Find UserRegion by user and region
     * @param user
     * @param region
     * @return
     */
    UserRegion findByUserAndRegion(User user, Region region);
}
