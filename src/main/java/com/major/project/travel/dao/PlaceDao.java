package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
public interface PlaceDao extends CommonHibernateInteface<Serializable, Place> {
    /**
     * find Place By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Place findPlaceByUid(String uid) throws DataNotFoundException;

    /**
     * find By Region
     * @param region
     * @return
     * @throws DataNotFoundException
     */
    List<Place> findByRegion(Region region) throws DataNotFoundException;

    /**
     * find By User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Place> findByUser(User user) throws DataNotFoundException;
}
