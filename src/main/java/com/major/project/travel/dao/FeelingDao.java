package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.PlaceUser;
import com.major.project.travel.model.UserRegion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
public interface FeelingDao extends CommonHibernateInteface<Serializable, Feeling> {
    /**
     * Find all feelings by UserRegion
     * @param userRegion
     * @return
     */
    List<Feeling> findAllByUserRegion (UserRegion userRegion);

    /**
     * Find all feelings by PlaceUser
     * @param placeUser
     * @return
     */
    List<Feeling> findAllByPlaceUser (PlaceUser placeUser);
}
