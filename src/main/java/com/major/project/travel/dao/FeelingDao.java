package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;

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

    /**
     * Find By User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Feeling> findPostByUser(User user) throws DataNotFoundException;

    /**
     * Find Feeling By Place
     * @param place
     * @return
     * @throws DataNotFoundException
     */
    List<Feeling> findFeelingByPlace(Place place) throws DataNotFoundException;
}
